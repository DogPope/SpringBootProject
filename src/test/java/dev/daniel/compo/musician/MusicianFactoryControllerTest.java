package dev.daniel.compo.musician;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.ArgumentMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MusicianRestController.class)
public class MusicianFactoryControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    JdbcClientMusicianRepository jcmr;
    private final List<Musician> pianists = new ArrayList<>();
    @BeforeEach
    void setUp() {
        pianists.add(new Musician(1,
                "Patrick",
                "Bezos",
                Country.YEMEN,
                Genre.POP,
                Gender.MALE,
                LocalDate.of(54,1,1),
                LocalDate.of(54,2,2)
                )
        );
    }

    @Test
    void findOneMusician() throws Exception {
        Musician musician = pianists.getFirst();
        when(jcmr.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(musician));
        mvc.perform(get("/api/musicians/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.musicianId", is(musician.getMusicianId())))
                .andExpect(jsonPath("$.firstName", is(musician.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(musician.getLastName())))
                .andExpect(jsonPath("$.country", is(musician.getCountry().toString())))
                .andExpect(jsonPath("$.genre", is(musician.getGenre().toString())))
                .andExpect(jsonPath("$.gender", is(musician.getGender().toString())))
                .andExpect(jsonPath("$.dateOfBirth", is(musician.getDateOfBirth().toString())))
                .andExpect(jsonPath("$.dateOfDeath", is(musician.getDateOfDeath().toString())));
    }

    @Test
    void shouldReturnInvalidId() throws Exception {
        mvc.perform(get("/api/musicians/9999"))
                .andExpect(status().isNotFound());
    }

}

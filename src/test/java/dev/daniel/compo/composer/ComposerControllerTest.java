package dev.daniel.compo.composer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Date;

@WebMvcTest(ComposerController.class)
public class ComposerControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    JdbcClientComposerRepository jccr;
    private final List<Composer> composers = new ArrayList<>();
    @BeforeEach
    void setUp() {
        composers.add(new Composer(1,
                "Patrick",
                "Bezos",
                Country.YEMEN,
                Genre.POP,
                Gender.MALE,
                new Date(54,01,01),
                new Date(54,02,02)));
    }

    @Test
    void findAll() throws Exception {
        when(jccr.findAll()).thenReturn(composers);
        mvc.perform(get("/api/composers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(composers.size())));
    }

    @Test
    void findOneComposer() throws Exception {
        Composer composer = composers.get(0);
        when(jccr.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(composer));
        mvc.perform(get("/api/composers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.composerId", is(composer.getComposerId())))
                .andExpect(jsonPath("$.firstName", is(composer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(composer.getLastName())))
                .andExpect(jsonPath("$.country", is(composer.getCountry().toString())))
                .andExpect(jsonPath("$.genre", is(composer.getGenre().toString())))
                .andExpect(jsonPath("$.gender", is(composer.getGender().toString())))
                .andExpect(jsonPath("$.dateOfBirth", is(composer.getDateOfBirth().toString())))
                .andExpect(jsonPath("$.dateOfDeath", is(composer.getDateOfDeath().toString())));
    }

    @Test
    void shouldReturnInvalidId() throws Exception {
        mvc.perform(get("/api/composers/9999"))
                .andExpect(status().isNotFound());
    }

    // I don't think there's any need to flesh this out too much. The HTTP files do the same job, as does the bash script.
}

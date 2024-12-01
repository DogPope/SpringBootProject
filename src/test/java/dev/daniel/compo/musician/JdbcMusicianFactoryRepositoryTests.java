package dev.daniel.compo.musician;

import dev.daniel.compo.TestDataUtil;
import dev.daniel.compo.instrument.Instrument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class JdbcMusicianFactoryRepositoryTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private JdbcClientMusicianRepository jcmr;
    @Test
    void testCreateMusicianSQL() {
        Musician musician1 = TestDataUtil.createMusicianA();
        jcmr.create(musician1);
        System.out.println(jcmr.findById(1));
        assertThat(musician1.getMusicianId().equals(1));
    }
    @Test
    void testUpdateMusicianSQL() {
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(new Instrument(7,"BAGPIPES"));
        Musician musician1 = new Musician(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE, LocalDate.of(1992,3, 5),LocalDate.of(1995,6,1), instruments
        );
        jcmr.update(musician1, musician1.getMusicianId());
        verify(jdbcTemplate).update("UPDATE musician SET first_name=?,last_name=?,country=?,genre=?,gender=?,year_of_birth=?,year_of_death=? WHERE musician_id=?",
                musician1.getFirstName(),musician1.getLastName(),musician1.getCountry().toString(),musician1.getGenre().toString(),musician1.getGender().toString(),musician1.getDateOfBirth(),musician1.getDateOfDeath(),musician1.getMusicianId());
    }
    @Test
    void testFindByIdSQL() {
        Musician musician1 = TestDataUtil.createMusicianA();
        jcmr.findById(musician1.getMusicianId());
        verify(jdbcTemplate).query(
                eq("""
                        SELECT
                            m.musician_id,
                            m.first_name,
                            m.last_name,
                            m.country,
                            m.genre,
                            m.gender,
                            m.year_of_birth,
                            m.year_of_death,
                            i.instrument_name
                        FROM musician m
                        JOIN musician_instrument mi ON m.musician_id = mi.musician_id
                        JOIN instrument i ON mi.instrument_id = i.instrument_id
                        WHERE m.musician_id = 1;
                        """),
                ArgumentMatchers.<JdbcClientMusicianRepository.MusicianRowMapper>any(),
                eq(10)
        );
    }
    @Test
    void testDeleteSql() {
        jcmr.delete(10);
        verify(jdbcTemplate).update(
                "DELETE FROM musician WHERE musician_id=?",
                10
        );
    }
}
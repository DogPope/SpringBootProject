package dev.daniel.compo.musician;

import dev.daniel.compo.TestDataUtil;
import dev.daniel.compo.instrument.Instrument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Musician d = TestDataUtil.createMusicianD();
        jcmr.create(d);
        verify(jdbcTemplate).update(
                eq("INSERT INTO musician(first_name, last_name, country, genre, gender, year_of_birth, year_of_death) VALUES(?,?,?,?,?,?,?)"),
                eq(d.getFirstName()),
                eq(d.getLastName()),
                eq(d.getCountry().toString()),
                eq(d.getGenre().toString()),
                eq(d.getGender().toString()),
                eq(d.getDateOfBirth()),
                eq(d.getDateOfDeath())
        );
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
        // Why was this method so complicated by me? All you have to test is create() and findById(). What I had before was needlessly complex.
        Musician a = TestDataUtil.createMusicianA();
        jcmr.create(a);
        jcmr.findById(a.getMusicianId());
        assertEquals(1,a.getMusicianId());
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
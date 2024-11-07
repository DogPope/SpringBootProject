package dev.daniel.compo.musician;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class JdbcMusicianFactoryRepositoryTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private JdbcClientMusicianRepository jcmr;
    @Test
    public void testCreateMusicianSQL() {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.ACCORDION);
        MusicianFactory musician1 = new Musician(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01), instruments
        );
        jcmr.create(musician1);
        verify(jdbcTemplate).update(
                eq("INSERT INTO musician(first_name, last_name, country, genre, gender, year_of_birth, year_of_death) VALUES(?,?,?,?,?,?,?)"),
                eq("Jeff"), eq("OShea"),eq(Country.ANDORRA.toString()),eq(Genre.THEATRE.toString()),eq(Gender.MALE.toString()),eq(new Date(1992,00,01)),eq(new Date(1995,00,01)),eq(("ACCORDION"))
        );
    }
    @Test
    public void testUpdateMusicianSQL() {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.ACCORDION);
        MusicianFactory musician1 = new Musician(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01), instruments
        );
        jcmr.update(musician1, musician1.getMusicianId());
        verify(jdbcTemplate).update("UPDATE musician SET first_name=?,last_name=?,country=?,genre=?,gender=?,year_of_birth=?,year_of_death=? WHERE musician_id=?",
                musician1.getFirstName(),musician1.getLastName(),musician1.getCountry().toString(),musician1.getGenre().toString(),musician1.getGender().toString(),musician1.getDateOfBirth(),musician1.getDateOfDeath(),musician1.getMusicianId());
    }
    @Test
    public void testFindByIdSQL() {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.ACCORDION);
        MusicianFactory musician1 = new Musician(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01), instruments
        );
        jcmr.findById(musician1.getMusicianId());
        verify(jdbcTemplate).query(
                eq("SELECT * FROM musician WHERE musician_id = ?"),
                ArgumentMatchers.<JdbcClientMusicianRepository.MusicianRowMapper>any(),
                eq(10)
        );
    }
    @Test
    public void testDeleteSql() {
        jcmr.delete(10);
        verify(jdbcTemplate).update(
                "DELETE FROM musician WHERE musician_id=?",
                10
        );
    }
}

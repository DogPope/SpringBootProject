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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class JdbcMusicianRepositoryTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private JdbcClient jdbcClient;
    @InjectMocks
    private JdbcClientMusicianRepository jccr;
    @Test
    public void testCreateComposerSQL() {
        Musician musician1 = new Pianist(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01)
        );
        jccr.create(musician1);
        verify(jdbcTemplate).update(
                eq("INSERT INTO composer(first_name, last_name, country, genre, gender, year_of_birth, year_of_death) VALUES(?,?,?,?,?,?,?)"),
                eq("Jeff"), eq("OShea"),eq(Country.ANDORRA.toString()),eq(Genre.THEATRE.toString()),eq(Gender.MALE.toString()),eq(new Date(1992,00,01)),eq(new Date(1995,00,01))
        );
    }
    @Test
    public void testUpdateComposerSQL() {
        Musician musician1 = new Pianist(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01)
        );
        jccr.update(musician1, musician1.getMusicianId());
        verify(jdbcTemplate).update("UPDATE composer SET first_name=?,last_name=?,country=?,genre=?,gender=?,year_of_birth=?,year_of_death=? WHERE composer_id=?",
                musician1.getFirstName(),musician1.getLastName(),musician1.getCountry().toString(),musician1.getGenre().toString(),musician1.getGender().toString(),musician1.getDateOfBirth(),musician1.getDateOfDeath(),musician1.getMusicianId());
    }
    @Test
    public void testFindByIdSQL() {
        Musician musician1 = new Pianist(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01)
        );
        jccr.findById(musician1.getMusicianId());
        verify(jdbcTemplate).query(
                eq("SELECT * FROM composer WHERE composer_id = ?"),
                ArgumentMatchers.<JdbcClientMusicianRepository.MusicianRowMapper>any(),
                eq(10)
        );
    }
    @Test
    public void testDeleteSql() {
        jccr.delete(10);
        verify(jdbcTemplate).update(
                "DELETE FROM composer WHERE composer_id=?",
                10
        );
    }
}

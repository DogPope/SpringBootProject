package dev.daniel.compo.composer;

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
public class JdbcComposerRepositoryTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private JdbcClient jdbcClient;
    @InjectMocks
    private JdbcClientComposerRepository jccr;
    @Test
    public void testCreateComposerSQL() {
        Composer composer1 = new Composer(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01)
        );
        jccr.create(composer1);
        verify(jdbcTemplate).update(
                eq("INSERT INTO composer(first_name, last_name, country, genre, gender, year_of_birth, year_of_death) VALUES(?,?,?,?,?,?,?)"),
                eq("Jeff"), eq("OShea"),eq(Country.ANDORRA.toString()),eq(Genre.THEATRE.toString()),eq(Gender.MALE.toString()),eq(new Date(1992,00,01)),eq(new Date(1995,00,01))
        );
    }
    @Test
    public void testUpdateComposerSQL() {
        Composer composer1 = new Composer(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01)
        );
        jccr.update(composer1, composer1.getComposerId());
        verify(jdbcTemplate).update("UPDATE composer SET first_name=?,last_name=?,country=?,genre=?,gender=?,year_of_birth=?,year_of_death=? WHERE composer_id=?",
                composer1.getFirstName(),composer1.getLastName(),composer1.getCountry().toString(),composer1.getGenre().toString(),composer1.getGender().toString(),composer1.getDateOfBirth(),composer1.getDateOfDeath(),composer1.getComposerId());
    }
    @Test
    public void testFindByIdSQL() {
        Composer composer1 = new Composer(
                10, "Jeff","OShea",Country.ANDORRA,Genre.THEATRE,Gender.MALE,new Date(1992,00,01),new Date(1995,00,01)
        );
        jccr.findById(composer1.getComposerId());
        verify(jdbcTemplate).query(
                eq("SELECT * FROM composer WHERE composer_id = ?"),
                ArgumentMatchers.<JdbcClientComposerRepository.ComposerRowMapper>any(),
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

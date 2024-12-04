package dev.daniel.compo.musician;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// Need to test whether all RowMapper columns come back with data.
// Rather than testing each method, just one method for all fields. If one works, they should all work.
public class RowMapperTests {
    @Test
    void testMapRow() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);

        when(rs.getInt("musician_id")).thenReturn(1);
        when(rs.getString("first_name")).thenReturn("John");
        when(rs.getString("last_name")).thenReturn("Doe");
        when(rs.getString("country")).thenReturn("VIETNAM");
        when(rs.getString("genre")).thenReturn("POP");
        when(rs.getString("gender")).thenReturn("OTHER");
        when(rs.getDate("year_of_birth")).thenReturn(java.sql.Date.valueOf(LocalDate.of(1990, 1, 1)));
        when(rs.getDate("year_of_death")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2050, 1, 1)));

        JdbcClientMusicianRepository.MusicianRowMapper mapper = new JdbcClientMusicianRepository.MusicianRowMapper();
        Musician musician = mapper.mapRow(rs, 1);

        assertNotNull(musician);
        assertEquals(1, musician.getMusicianId());
        assertEquals("John", musician.getFirstName());
        assertEquals("Doe", musician.getLastName());
        assertEquals(Country.VIETNAM, musician.getCountry());
        assertEquals(Genre.POP, musician.getGenre());
        assertEquals(Gender.OTHER, musician.getGender());
        assertEquals(LocalDate.of(1990, 1, 1), musician.getDateOfBirth());
        assertEquals(LocalDate.of(2050, 1, 1), musician.getDateOfDeath());
    }
}

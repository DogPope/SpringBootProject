package dev.daniel.compo.musician;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Component
public class JdbcClientMusicianRepository implements MusicianRepository {
    private final JdbcTemplate jdbcTemplate;
    private final JdbcClient jdbcClient;
    public JdbcClientMusicianRepository(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate){
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Musician> findAll() {
        return jdbcTemplate.query("SELECT * FROM musician",
                (rs, rowNum) -> {
                    Musician musician = new Musician();
                    musician.setMusicianId(rs.getInt("musician_id"));
                    musician.setFirstName(rs.getString("first_name"));
                    musician.setLastName(rs.getString("last_name"));
                    musician.setCountry(Country.valueOf(rs.getString("country")));
                    musician.setGenre(Genre.valueOf(rs.getString("genre")));
                    musician.setGender(Gender.valueOf(rs.getString("gender")));
                    musician.setDateOfBirth(rs.getDate("year_of_birth"));
                    musician.setDateOfDeath(rs.getDate("year_of_death"));
                    return musician;
                });
    }
    @Override
    public Optional<Musician> findById(Integer id) {
        List<Musician> musician = jdbcTemplate.query(
                "SELECT * FROM musician WHERE musician_id = ?",
                new MusicianRowMapper(), id
        );
        return musician.stream().findFirst();
    }
    public void create(Musician musician){
        jdbcTemplate.update(
                "INSERT INTO musician(first_name, last_name, country, genre, gender, year_of_birth, year_of_death) VALUES(?,?,?,?,?,?,?)",
                musician.getFirstName(),musician.getLastName(),musician.getCountry().toString(),musician.getGenre().toString(),musician.getGender().toString(),musician.getDateOfBirth(), musician.getDateOfDeath());
    }
    public void update(Musician musician, Integer id){
        jdbcTemplate.update("UPDATE musician SET first_name=?,last_name=?,country=?,genre=?,gender=?,year_of_birth=?,year_of_death=? WHERE musician_id=?",
                musician.getFirstName(),musician.getLastName(),musician.getCountry().toString(),musician.getGenre().toString(),musician.getGender().toString(),musician.getDateOfBirth(),musician.getDateOfDeath(), id);
    }
    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM musician WHERE musician_id=?", id);
    }
    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM musician").query().listOfRows().size();
    }
    public void saveAll(List<Musician> musicians){
        musicians.forEach(this::create);
    }

    public static class MusicianRowMapper implements RowMapper<Musician> {
        @Override
        public Musician mapRow(ResultSet rs, int rowNum) throws SQLException {
            Musician musician = new Musician();
            musician.setMusicianId(rs.getInt("musician_id"));
            musician.setFirstName(rs.getString("first_name"));
            musician.setLastName(rs.getString("last_name"));
            musician.setCountry(Country.valueOf(rs.getString("country")));
            musician.setGenre(Genre.valueOf(rs.getString("genre")));
            musician.setGender(Gender.valueOf(rs.getString("gender")));
            musician.setDateOfBirth(rs.getDate("year_of_birth"));
            musician.setDateOfDeath(rs.getDate("year_of_death"));
            return musician;
        }
    }
}

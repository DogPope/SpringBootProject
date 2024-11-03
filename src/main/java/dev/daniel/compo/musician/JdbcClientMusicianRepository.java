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
    private static final Logger log = LoggerFactory.getLogger(MusicianRepository.class);
    private final JdbcTemplate jdbcTemplate;
    private final JdbcClient jdbcClient;
    public JdbcClientMusicianRepository(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate){
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Musician> findAll() {
        return jdbcTemplate.query("SELECT * FROM composer",
                (rs, rowNum) -> {
                    Musician musician = new Pianist();
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
        List<Musician> composer = jdbcTemplate.query(
                "SELECT * FROM composer WHERE composer_id = ?",
                new MusicianRowMapper(), id
        );
        return composer.stream().findFirst();
    }
    public void create(Musician composer){
        jdbcTemplate.update(
                "INSERT INTO composer(first_name, last_name, country, genre, gender, year_of_birth, year_of_death) VALUES(?,?,?,?,?,?,?)",
                composer.getFirstName(),composer.getLastName(),composer.getCountry().toString(),composer.getGenre().toString(),composer.getGender().toString(),composer.getDateOfBirth(), composer.getDateOfDeath());
                log.info(composer.toString());
    }
    public void update(Musician composer, Integer id){
        log.info(composer.toString());
        jdbcTemplate.update("UPDATE composer SET first_name=?,last_name=?,country=?,genre=?,gender=?,year_of_birth=?,year_of_death=? WHERE composer_id=?",
                composer.getFirstName(),composer.getLastName(),composer.getCountry().toString(),composer.getGenre().toString(),composer.getGender().toString(),composer.getDateOfBirth(),composer.getDateOfDeath(), id);
        log.info(composer.toString());
    }
    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM composer WHERE composer_id=?", id);
    }
    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM composer").query().listOfRows().size();
    }
    public void saveAll(List<Musician> composers){
        composers.forEach(this::create);
    }

    public static class MusicianRowMapper implements RowMapper<Musician> {
        @Override
        public Musician mapRow(ResultSet rs, int rowNum) throws SQLException {
            Musician musician = new Pianist();
            musician.setMusicianId(rs.getInt("composer_id"));
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

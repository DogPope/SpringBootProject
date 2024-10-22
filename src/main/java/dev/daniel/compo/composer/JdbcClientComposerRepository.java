package dev.daniel.compo.composer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
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
public class JdbcClientComposerRepository implements ComposerRepository{
    private static final Logger log = LoggerFactory.getLogger(ComposerRepository.class);
    private final JdbcTemplate jdbcTemplate;
    private final JdbcClient jdbcClient;
    public JdbcClientComposerRepository(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate){
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Composer> findAll() {
        return jdbcTemplate.query("SELECT * FROM composer",
                (rs, rowNum) -> {
                    Composer composer = new Composer();
                    composer.setComposerId(rs.getInt("composer_id"));
                    composer.setFirstName(rs.getString("first_name"));
                    composer.setLastName(rs.getString("last_name"));
                    composer.setCountry(Country.valueOf(rs.getString("country")));
                    composer.setGenre(Genre.valueOf(rs.getString("genre")));
                    composer.setGender(Gender.valueOf(rs.getString("gender")));
                    composer.setDateOfBirth(rs.getDate("year_of_birth"));
                    composer.setDateOfDeath(rs.getDate("year_of_death"));
                    return composer;
                });
    }
    @Override
    public Optional<Composer> findById(Integer id) {
        List<Composer> composer = jdbcTemplate.query(
                "SELECT * FROM composer WHERE composer_id = ?",
                new ComposerRowMapper(), id
        );
        return composer.stream().findFirst();
    }
    public void create(Composer composer){
        jdbcTemplate.update(
                "INSERT INTO composer(first_name, last_name, country, genre, gender, year_of_birth, year_of_death) VALUES(?,?,?,?,?,?,?)",
                composer.getFirstName(),composer.getLastName(),composer.getCountry().toString(),composer.getGenre().toString(),composer.getGender().toString(),composer.getDateOfBirth(), composer.getDateOfDeath());
                log.info(composer.toString());
    }
    public void update(Composer composer, Integer id){
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
    public void saveAll(List<Composer> composers){
        composers.forEach(this::create);
    }

    public static class ComposerRowMapper implements RowMapper<Composer> {
        @Override
        public Composer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Composer composer = new Composer();
            composer.setComposerId(rs.getInt("composer_id"));
            composer.setFirstName(rs.getString("first_name"));
            composer.setLastName(rs.getString("last_name"));
            composer.setCountry(Country.valueOf(rs.getString("country")));
            composer.setGenre(Genre.valueOf(rs.getString("genre")));
            composer.setGender(Gender.valueOf(rs.getString("gender")));
            composer.setDateOfBirth(rs.getDate("year_of_birth"));
            composer.setDateOfDeath(rs.getDate("year_of_death"));
            return composer;
        }
    }
}

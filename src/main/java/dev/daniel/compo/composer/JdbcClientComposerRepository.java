package dev.daniel.compo.composer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
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
    public Optional<Composer> findById(Integer id) {
        List<Composer> composer = jdbcTemplate.query(
                "SELECT * FROM composer WHERE composer_id = ?",
                new ComposerRowMapper(), id
        );
        return composer.stream().findFirst();
    }
    public void create(Composer composer){
        System.out.println(composer.toString());
        var created = jdbcClient.sql(
                "INSERT INTO composer(first_name, last_name, country, genre, gender, year_of_birth, year_of_death)" +
                        "VALUES(?,?,?,?,?,?,?)")
                .params(List.of(composer.getFirstName(),composer.getLastName(),composer.getCountry().toString(),composer.getGenre().toString(),composer.getGender().toString(),composer.getDateOfBirth(), composer.getDateOfDeath()))
                .update();
        Assert.state(created == 1, "Failed to create Composer: " + composer.getFirstName() + " " + composer.getLastName());
    }
    public void update(Composer composer, Integer id){
        System.out.println(composer.toString());
        var updated = jdbcClient.sql("UPDATE composer SET first_name=?,last_name=?,country=?,genre=?,gender=?,year_of_birth=?,year_of_death=? WHERE composer_id=?")
                .params(List.of(composer.getFirstName(),composer.getLastName(),composer.getCountry().toString(),composer.getGenre().toString(),composer.getGender().toString(),composer.getDateOfBirth(),composer.getDateOfDeath(), id))
                .update();
        Assert.state(updated == 1, "Failed to Update Composer: " + composer.getComposerId());
    }
    public void delete(Integer id){
        var deleted = jdbcTemplate.update("DELETE FROM composer WHERE composer_id=?", id);
        Assert.state(deleted == 1, "Failed to Delete Composer: " + id);
    }
    public int count() {
        return jdbcClient.sql("SELECT * FROM composer").query().listOfRows().size();
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

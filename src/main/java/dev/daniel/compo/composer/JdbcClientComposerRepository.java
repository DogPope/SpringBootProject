package dev.daniel.compo.composer;
import dev.daniel.compo.piece.Piece;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;

@Repository
public class JdbcClientComposerRepository implements ComposerRepository{
    private static final Logger log = LoggerFactory.getLogger(ComposerRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcClient jdbcClient;

    public JdbcClientComposerRepository(JdbcClient jdbcClient, NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }
    /*public List<Composer> findAll() {
        return jdbcClient.sql("SELECT * FROM composer")
                .query(Composer.class)
                .list();
    }*/
    public List<Composer> findAll() {
        // No other way to do this but
        return jdbcTemplate.query("SELECT * FROM composer",
                (rs, rowNum) -> {
                    Composer composer = new Composer();
                    composer.setComposerId(rs.getInt("composer_id"));
                    composer.setFirstName(rs.getString("first_name"));
                    composer.setLastName(rs.getString("last_name"));
                    composer.setCountry(Country.valueOf(rs.getString("country")));
                    composer.setGenre(Genre.valueOf(rs.getString("genre")));
                    composer.setGender(Gender.valueOf(rs.getString("gender")));

                    Calendar yearOfBirth = Calendar.getInstance();
                    yearOfBirth.setTime(rs.getDate("year_of_birth"));
                    composer.setDateOfBirth(yearOfBirth);

                    Calendar yearOfDeath = null;
                    if (rs.getDate("year_of_death") != null) {
                        yearOfDeath = Calendar.getInstance();
                        yearOfDeath.setTime(rs.getDate("year_of_death"));
                    }
                    composer.setDateOfDeath(yearOfDeath);

                    return composer;
                });
    }
    public Optional<Composer> findComposerById(Integer id){
        return jdbcClient.sql("SELECT * FROM composer WHERE composer_id=:id")
                .param("id",id)
                .query(Composer.class)
                .optional();
    }

    // Suggest editing this method. It keeps throwing errors.
    public void create(Composer composer){
        var updated = jdbcClient.sql(
                "INSERT INTO Composer(composer_id,first_name,last_name,country,genre,gender,date_of_birth,date_of_death) " +
                        "VALUES(?,?,?,?,?,?,?,?)")
                .params(List.of(composer.getComposerId(),composer.getFirstName(),composer.getLastName(),composer.getCountry(),composer.getGenre(),composer.getGender(),composer.getDateOfBirth(), composer.getDateOfDeath()))
                .update();
        Assert.state(updated == 1, "Failed to create Composer: " + composer.getFirstName() + " " + composer.getLastName());
    }
    // I've finally found out what it was. The last_name in the schema was called second_name by mistake. Simple error, simple fix.
    public void update(Composer composer, Integer id){
        var updated = jdbcClient.sql(
                        "UPDATE Composer SET " +
                                "first_name = ?," +
                                "last_name = ?," +
                                "country = ?," +
                                "genre = ?," +
                                "gender = ?," +
                                "date_of_birth = ?," +
                                "date_of_death = ?" +
                                "WHERE composer_id = ?")
                .params(List.of(composer.getFirstName(),composer.getLastName(),composer.getCountry(),composer.getGenre(),composer.getGender(),composer.getDateOfBirth(), composer.getDateOfDeath(), id))
                .update();
        Assert.state(updated == 1, "Failed to update Composer: " + composer.getFirstName() + " " + composer.getLastName());
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("DELETE FROM Composer WHERE composer_id = :id")
                .param(":id", id)
                .update();
        Assert.state(updated == 1, "Failed to Delete Composer: " + id);
    }
    // These are case-sensitive, are you fucking joking? Why did changing the case fix the error?
    public int count() {
        return jdbcClient.sql("SELECT * FROM composer").query().listOfRows().size();
    }
    public void saveAll(List<Composer> composers){
        composers.forEach(this::create);
    }
}

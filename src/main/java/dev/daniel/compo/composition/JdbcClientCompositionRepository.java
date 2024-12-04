package dev.daniel.compo.composition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientCompositionRepository implements CompositionRepository{
    private static final Logger log = LoggerFactory.getLogger(JdbcClientCompositionRepository.class);
    private final JdbcClient jdbcClient;
    private final JdbcTemplate jdbcTemplate;
    public JdbcClientCompositionRepository(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate){
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Composition> findAll(){
        return jdbcClient.sql("SELECT * FROM composition")
                .query(Composition.class)
                .list();
    }
    public Optional<Composition> findCompositionById(Integer id){
        return jdbcClient.sql("SELECT * FROM composition WHERE composition_id=:id")
                .param("id",id)
                .query(Composition.class)
                .optional();
    }
    public void create(Composition composition){
        log.info(composition.toString());
        var created = jdbcClient.sql(
                        "INSERT INTO composition(title, year, musician_id) VALUES(?,?,?)")
                .params(List.of(composition.getTitle(), composition.getYear(), composition.getMusicianId()))
                .update();
        Assert.state(created == 1, "Failed to create Piece: " + composition.getTitle());
    }
    public void update(Composition composition, Integer id){
        jdbcTemplate.update(
                "UPDATE composition SET title=?, year=?, musician_id=? WHERE composition_id=?",
                composition.getTitle(), composition.getYear(), composition.getMusicianId(), id
        );
        log.info(composition.toString());
    }
    public void delete(Integer id){
        var deleted = jdbcTemplate.update("DELETE FROM composition WHERE composition_id=?", id);
        Assert.state(deleted == 1, "Failed to Delete Composition: " + id);
    }
    public int count() {
        return jdbcClient.sql("SELECT * FROM composition").query().listOfRows().size();
    }
    public void saveAll(List<Composition> compositions){
        compositions.forEach(this::create);
    }
}

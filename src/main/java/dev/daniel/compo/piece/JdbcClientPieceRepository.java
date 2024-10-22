package dev.daniel.compo.piece;

import dev.daniel.compo.composer.ComposerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientPieceRepository implements PieceRepository{
    private static final Logger log = LoggerFactory.getLogger(PieceRepository.class);
    private final JdbcClient jdbcClient;
    private final JdbcTemplate jdbcTemplate;
    public JdbcClientPieceRepository(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate){
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Piece> findAll(){
        return jdbcClient.sql("SELECT * FROM piece")
                .query(Piece.class)
                .list();
    }
    public Optional<Piece> findPieceById(Integer id){
        return jdbcClient.sql("SELECT * FROM piece WHERE piece_id=:id")
                .param("id",id)
                .query(Piece.class)
                .optional();
    }
    public void create(Piece piece){
        log.info(piece.toString());
        var created = jdbcClient.sql(
                        "INSERT INTO piece(title, year, composer_id) VALUES(?,?,?)")
                .params(List.of(piece.getTitle(), piece.getYear(), piece.getComposerId()))
                .update();
        Assert.state(created == 1, "Failed to create Piece: " + piece.getTitle());
    }
    public void update(Piece piece, int id){
        jdbcTemplate.update(
                "UPDATE piece SET title=?, year=?, composer_id=? WHERE piece_id=?",
                piece.getTitle(), piece.getYear(), piece.getComposerId(), id
        );
        log.info(piece.toString());
    }
    public void delete(Integer id){
        var deleted = jdbcTemplate.update("DELETE FROM piece WHERE piece_id=?", id);
        Assert.state(deleted == 1, "Failed to Delete Piece: " + id);
    }
    public int count() {
        return jdbcClient.sql("SELECT * FROM piece").query().listOfRows().size();
    }
    public void saveAll(List<Piece> pieces){
        pieces.forEach(this::create);
    }
}

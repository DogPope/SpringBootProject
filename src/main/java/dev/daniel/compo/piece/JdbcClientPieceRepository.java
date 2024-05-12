package dev.daniel.compo.piece;

import dev.daniel.compo.composer.ComposerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientPieceRepository implements PieceRepository{
    private static final Logger log = LoggerFactory.getLogger(ComposerRepository.class);
    private final JdbcClient jdbcClient;
    public JdbcClientPieceRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
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
        var updated = jdbcClient.sql(
                        "INSERT INTO Piece(piece_id,title,year,composerId) " +
                                "VALUES(?,?,?,?)")
                .params(List.of(piece.getPieceId(), piece.getTitle(), piece.getYear(), piece.getComposerId()))
                .update();
        Assert.state(updated == 1, "Failed to create Piece: " + piece.getTitle());
    }
    public void update(Piece piece, Integer id){
        var updated = jdbcClient.sql(
                        "UPDATE Piece SET " +
                                "title = ?," +
                                "year = ?," +
                                "WHERE piece_id = ?")
                .params(List.of(piece.getTitle(),piece.getYear()))
                .update();
        Assert.state(updated == 1, "Failed to create Piece: " + piece.getTitle());
    }
    public void delete(Integer id){
        var updated = jdbcClient.sql("DELETE FROM piece WHERE piece_id = :id")
                .param(":id", id)
                .update();
        Assert.state(updated == 1, "Failed to Delete Piece: " + id);
    }
    public int count() {
        return jdbcClient.sql("SELECT * FROM piece").query().listOfRows().size();
    }
    public void saveAll(List<Piece> pieces){
        pieces.forEach(this::create);
    }
}

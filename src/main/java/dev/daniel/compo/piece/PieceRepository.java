package dev.daniel.compo.piece;

import java.util.List;
import java.util.Optional;

public interface PieceRepository {
    List<Piece> findAll();
    Optional<Piece> findPieceById(Integer id);
    void create(Piece piece);
    void update(Piece piece, Integer id);
    void delete(Integer id);
    int count();
    void saveAll(List<Piece> pieces);
}

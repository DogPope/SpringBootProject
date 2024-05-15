package dev.daniel.compo.piece;

import dev.daniel.compo.composer.ComposerNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pieces")
public class PieceController {
    private final JdbcClientPieceRepository pieceRepository;
    public PieceController(JdbcClientPieceRepository pieceRepository){
        this.pieceRepository = pieceRepository;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Piece piece){
        pieceRepository.create(piece);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Piece piece, @PathVariable int id){
        pieceRepository.update(piece, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        pieceRepository.delete(id);
    }
    @GetMapping("")
    List<Piece> findAll(){
        return pieceRepository.findAll();
    }
    @GetMapping("/{id}")
    Piece findPieceById(@PathVariable Integer id){
        Optional<Piece> piece = pieceRepository.findPieceById(id);
        if(piece.isEmpty())
            throw new ComposerNotFoundException();
        return piece.get();
    }
}

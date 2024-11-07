package dev.daniel.compo.composition;

import dev.daniel.compo.musician.MusicianNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pieces")
public class CompositionController {
    private final JdbcClientCompositionRepository compositionRepository;
    public CompositionController(JdbcClientCompositionRepository compositionRepository){
        this.compositionRepository = compositionRepository;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Composition composition){
        compositionRepository.create(composition);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Composition composition, @PathVariable int id){
        compositionRepository.update(composition, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        compositionRepository.delete(id);
    }
    @GetMapping("")
    List<Composition> findAll(){
        return compositionRepository.findAll();
    }
    @GetMapping("/{id}")
    Composition findCompositionById(@PathVariable Integer id){
        Optional<Composition> composition = compositionRepository.findCompositionById(id);
        if(composition.isEmpty())
            throw new MusicianNotFoundException();
        return composition.get();
    }
}

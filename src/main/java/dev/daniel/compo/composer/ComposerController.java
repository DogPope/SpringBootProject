package dev.daniel.compo.composer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/composers")
public class ComposerController {
    private final JdbcClientComposerRepository composerRepository;
    public ComposerController(JdbcClientComposerRepository composerRepository){
        this.composerRepository = composerRepository;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Composer composer){
        composerRepository.create(composer);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Composer composer, @PathVariable Integer id){
        composerRepository.update(composer, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        composerRepository.delete(id);
    }
    @GetMapping("")
    List<Composer> findAll(){
        return composerRepository.findAll();
    }
    @GetMapping("/{id}")
    Composer findById(@PathVariable Integer id) {
        Optional<Composer> composer = composerRepository.findById(id);
        if(composer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found.");
        }
        return composer.get();
    }
    @GetMapping("/easteregg")
    String easterEgg(){
        return "There are no Easter Eggs here. Go away!";
    }
}

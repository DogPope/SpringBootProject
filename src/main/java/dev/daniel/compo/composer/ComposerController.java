package dev.daniel.compo.composer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

// Couldn't find eastereggs for a while. When @RequestMapping was added to this class, the WHOLE address now rests AFTER 8080/api/composers. Hope that helps.
@RestController
@RequestMapping("/api/composers")
public class ComposerController {
    private final JdbcClientComposerRepository composerRepository;
    public ComposerController(JdbcClientComposerRepository composerRepository){
        this.composerRepository = composerRepository;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Composer composer){
        composerRepository.create(composer);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Composer composer, @PathVariable Integer id){
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
    Composer findComposerById(@PathVariable Integer id){
        Optional<Composer> composer = composerRepository.findComposerById(id);
        if(composer.isEmpty())
            throw new ComposerNotFoundException();
        return composer.get();
    }
    @GetMapping("/easteregg")
    String easterEgg(){
        return "There are no Easter Eggs here. Go away!";
    }
}

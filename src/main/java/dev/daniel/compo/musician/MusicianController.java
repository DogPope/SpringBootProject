package dev.daniel.compo.musician;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/musicians")
public class MusicianController {
    private final JdbcClientMusicianRepository musicianRepository;
    public MusicianController(JdbcClientMusicianRepository musicianRepository){
        this.musicianRepository = musicianRepository;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody MusicianFactory musician){
        musicianRepository.create(musician);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody MusicianFactory musician, @PathVariable Integer id){
        musicianRepository.update(musician, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        musicianRepository.delete(id);
    }
    @GetMapping("")
    List<MusicianFactory> findAll(){
        return musicianRepository.findAll();
    }
    @GetMapping("/{id}")
    MusicianFactory findById(@PathVariable Integer id) {
        Optional<MusicianFactory> musician = musicianRepository.findById(id);
        if(musician.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Musician not found.");
        }
        return musician.get();
    }
    @GetMapping("/easteregg")
    String easterEgg(){
        return "There are no Easter Eggs here. Go away!";
    }
}

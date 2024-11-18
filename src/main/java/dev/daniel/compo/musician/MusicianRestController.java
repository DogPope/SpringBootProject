package dev.daniel.compo.musician;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

// These two lines causing the problem. Changing RestController to Controller and removing RequestMapping allow you to hit http://localhost:5000/index, etc.
@RestController
@RequestMapping("/api/musicians")
public class MusicianRestController {
    private final JdbcClientMusicianRepository musicianRepository;
    public MusicianRestController(JdbcClientMusicianRepository musicianRepository){
        this.musicianRepository = musicianRepository;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Musician musician){
        musicianRepository.create(musician);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Musician musician, @PathVariable Integer id){
        musicianRepository.update(musician, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        musicianRepository.delete(id);
    }
    @GetMapping("")
    List<Musician> findAll(){
        return musicianRepository.findAll();
    }
    @GetMapping("/{id}")
    Musician findById(@PathVariable Integer id) {
        Optional<Musician> musician = musicianRepository.findById(id);
        if(musician.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Musician not found.");
        }
        return musician.get();
    }
    @GetMapping("/easteregg")
    String easterEgg(){
        return "easteregg.html";
    }
    //http://localhost:5000/api/musicians/index
    // That does hit, but literally only returns "index.html". Not the actual page.
    @GetMapping("index")
    String viewMusicians(Model model){
        System.out.println("Bringing back Index.");
        model.addAttribute("user","69420");
        return "index.html";
    }
}
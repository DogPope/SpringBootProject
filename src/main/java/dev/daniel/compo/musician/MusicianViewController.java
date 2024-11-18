package dev.daniel.compo.musician;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/musicians")
public class MusicianViewController {
    private final JdbcClientMusicianRepository musicianRepository;
    public MusicianViewController(JdbcClientMusicianRepository musicianRepository){
        this.musicianRepository = musicianRepository;
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
        model.addAttribute("user","69420");
        return "index.html";
    }
}
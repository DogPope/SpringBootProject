package dev.daniel.compo.musician;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    String findAll(Model model){
        List<Musician> musicians = musicianRepository.findAll();
        if(musicians.isEmpty()){
            return "error.html";
        }
        model.addAttribute("musician",musicians);
        return "allMusicians.html";
    }
    @GetMapping("easteregg")
    String easterEgg(){
        return "easteregg.html";
    }

    //http://localhost:5000/musicians/1 2,3,4 etc
    @GetMapping("/{id}")
    String findById(Model model, @PathVariable Integer id){
        Optional<Musician> musician = musicianRepository.findById(id);
        if (musician.isPresent()) {
            model.addAttribute("musician", musician.get());
            return "oneMusician.html";
        }else{
            return "error.html";
        }
    }
}
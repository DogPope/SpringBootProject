package dev.daniel.compo.instrument;

import dev.daniel.compo.musician.MusicianNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {
    private final JdbcClientInstrumentRepository instrumentRepository;
    public InstrumentController(JdbcClientInstrumentRepository instrumentRepository){
        this.instrumentRepository = instrumentRepository;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Instrument instrument){
        instrumentRepository.create(instrument);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Instrument instrument, @PathVariable Integer id){
        instrumentRepository.update(instrument, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        instrumentRepository.delete(id);
    }
    @GetMapping("")
    List<Instrument> findAll(){
        return instrumentRepository.findAll();
    }
    @GetMapping("/{id}")
    Instrument findInstrumentById(@PathVariable Integer id){
        Optional<Instrument> composition = instrumentRepository.findInstrumentById(id);
        if(composition.isEmpty())
            throw new MusicianNotFoundException();
        return composition.get();
    }
}

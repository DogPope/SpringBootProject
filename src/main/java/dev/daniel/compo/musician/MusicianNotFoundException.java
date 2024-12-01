package dev.daniel.compo.musician;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MusicianNotFoundException extends RuntimeException{
    public MusicianNotFoundException(){
        super("Musician Not Found!");
    }
}

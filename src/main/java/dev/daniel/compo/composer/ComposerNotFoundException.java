package dev.daniel.compo.composer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ComposerNotFoundException extends RuntimeException{
    public ComposerNotFoundException(){
        super("Composer Not Found!");
    }
}

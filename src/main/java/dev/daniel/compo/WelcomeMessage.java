package dev.daniel.compo;

import org.springframework.stereotype.Component;

@Component
public class WelcomeMessage {
    public String easterEgg(){
        return "There are no Easter Eggs here. Go away!";
    }
}

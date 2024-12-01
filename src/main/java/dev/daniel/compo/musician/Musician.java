package dev.daniel.compo.musician;

import dev.daniel.compo.instrument.Instrument;

import java.time.LocalDate;
import java.util.List;

public class Musician extends MusicianFactory {
    public Musician(){
        this.firstName = "";
        this.lastName = "";
        this.country = null;
        this.genre = null;
        this.gender = null;
        this.dateOfBirth = null;
        this.dateOfDeath = null;
        this.instruments = null;
    }
    public Musician(Integer musicianId, String firstName, String lastName, Country country, Genre genre, Gender gender, LocalDate dateOfBirth, LocalDate dateOfDeath, List<Instrument> instruments) {
        super(musicianId,firstName,lastName,country,genre,gender,dateOfBirth,dateOfDeath, instruments);
    }
    public Musician(String firstName, String lastName, Country country, Genre genre, Gender gender, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        super(firstName,lastName,country,genre,gender,dateOfBirth,dateOfDeath);
    }
    public Musician(Integer musicianId, String firstName, String lastName, Country country, Genre genre, Gender gender, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        super(musicianId,firstName,lastName,country,genre,gender,dateOfBirth,dateOfDeath);
    }
}
package dev.daniel.compo.musician;

import dev.daniel.compo.instrument.Instrument;

import java.sql.Date;
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
    public Musician(Integer musicianId, String firstName, String lastName, Country country, Genre genre, Gender gender, Date dateOfBirth, Date dateOfDeath, List<Instrument> instruments) {
        super(musicianId,firstName,lastName,country,genre,gender,dateOfBirth,dateOfDeath, instruments);
    }
}
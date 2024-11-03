package dev.daniel.compo.musician;

import java.sql.Date;

public class Pianist extends Musician {
    public Pianist(){
        this.firstName = "";
        this.lastName = "";
        this.country = null;
        this.genre = null;
        this.gender = null;
        this.dateOfBirth = null;
        this.dateOfDeath = null;
    }
    public Pianist(Integer id, String firstName, String lastName, Country country, Genre genre, Gender gender, Date dateOfBirth, Date dateOfDeath){
        super(id,firstName,lastName,country,genre,gender,dateOfBirth,dateOfDeath);
    }
}
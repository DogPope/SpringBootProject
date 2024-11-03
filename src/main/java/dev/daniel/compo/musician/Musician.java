package dev.daniel.compo.musician;

import java.sql.Date;

public abstract class Musician {
    Integer musicianId;
    String firstName;
    String lastName;
    Country country;
    Genre genre;
    Gender gender;
    Date dateOfBirth;
    Date dateOfDeath;

    public Musician(){
        this.firstName = "";
        this.lastName = "";
        this.country = null;
        this.genre = null;
        this.gender = null;
        this.dateOfBirth = null;
        this.dateOfDeath = null;
    }
    public Musician(Integer musicianId, String firstName, String lastName, Country country, Genre genre, Gender gender, Date dateOfBirth, Date dateOfDeath){
        this.musicianId = musicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.genre = genre;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = null;
    }
    public Integer getMusicianId() {
        return musicianId;
    }
    public void setMusicianId(Integer musicianId) {
        this.musicianId = musicianId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Date getDateOfDeath() {
        return dateOfDeath;
    }
    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    public String toString(){
        return "Musician ID: " + this.getMusicianId() + "\nFirst Name: " + this.getFirstName() + "\nLast Name: " + this.getLastName() + "\nCountry: " + this.getCountry()
                + "\nGenre: " + this.getGenre() + "\nGender: " + this.getGender() + "\nDate of Birth: " + this.getDateOfBirth() + "\nDate of Death: " + this.getDateOfDeath();
    }
}

package dev.daniel.compo.musician;

import dev.daniel.compo.instrument.Instrument;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class MusicianFactory {
    Integer musicianId;
    String firstName;
    String lastName;
    Country country;
    Genre genre;
    Gender gender;
    LocalDate dateOfBirth;
    LocalDate dateOfDeath;
    List<Instrument> instruments;

    protected MusicianFactory(){
        this.firstName = "";
        this.lastName = "";
        this.country = null;
        this.genre = null;
        this.gender = null;
        this.dateOfBirth = null;
        this.dateOfDeath = null;
        this.instruments = new ArrayList<>();
    }
    protected MusicianFactory(Integer musicianId, String firstName, String lastName, Country country, Genre genre, Gender gender, LocalDate dateOfBirth, LocalDate dateOfDeath, List<Instrument> instruments){
        this.musicianId = musicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.genre = genre;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.instruments = instruments;
    }
    protected MusicianFactory(Integer musicianId, String firstName, String lastName, Country country, Genre genre, Gender gender, LocalDate dateOfBirth, LocalDate dateOfDeath){
        this.musicianId = musicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.genre = genre;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }
    protected MusicianFactory(String firstName, String lastName, Country country, Genre genre, Gender gender, LocalDate dateOfBirth, LocalDate dateOfDeath){
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.genre = genre;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
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
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }
    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    public List<Instrument> getInstruments() {
        if (instruments == null) {
            instruments = new ArrayList<>();
        }
        return instruments;
    }
    public void setInstruments(List<Instrument> instruments) {this.instruments = instruments;}
    public String toString(){
        return "Musician ID: " + this.getMusicianId() + "\nFirst Name: " + this.getFirstName() + "\nLast Name: " + this.getLastName() + "\nCountry: " + this.getCountry()
                + "\nGenre: " + this.getGenre() + "\nGender: " + this.getGender() + "\nDate of Birth: " + this.getDateOfBirth() + "\nDate of Death: " + this.getDateOfDeath()
                + "\nInstruments: " + this.getInstruments();
    }
}

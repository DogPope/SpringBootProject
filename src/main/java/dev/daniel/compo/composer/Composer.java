package dev.daniel.compo.composer;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.Date;
public class Composer{
        @Positive
        private Integer composerId;
        @NotEmpty
        private String firstName;
        private String lastName;
        private Country country;
        private Genre genre;
        private Gender gender;
        private Date dateOfBirth;
        @Nullable
        private Date dateOfDeath;
        public Composer(){
                this.firstName = "";
                this.lastName = "";
                this.country = null;
                this.genre = null;
                this.gender = null;
                this.dateOfBirth = null;
                this.dateOfDeath = null;
        }
        public Composer(Integer id, String firstName, String lastName, Country country, Genre genre, Gender gender, Date dateOfBirth, Date dateOfDeath){
                this.composerId = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.country = country;
                this.genre = genre;
                this.gender = gender;
                this.dateOfBirth = dateOfBirth;
                this.dateOfDeath = dateOfDeath;
        }
        public Integer getComposerId() {
                return composerId;
        }
        public void setComposerId(Integer composerId) {
                this.composerId = composerId;
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
        @Nullable
        public Date getDateOfDeath() {
                return dateOfDeath;
        }
        public void setDateOfDeath(@Nullable Date dateOfDeath) {
                this.dateOfDeath = dateOfDeath;
        }
}
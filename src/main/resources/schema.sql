DROP DATABASE IF EXISTS musicians;
CREATE DATABASE musicians;
USE musicians;

DROP TABLE IF EXISTS musician_instrument;
DROP TABLE IF EXISTS instrument;
DROP TABLE IF EXISTS composition;
DROP TABLE IF EXISTS musician;

CREATE TABLE musician (
                          musician_id smallint(6) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          first_name varchar(20) NOT NULL,
                          last_name varchar(20) DEFAULT NULL,
                          country varchar(15) NOT NULL,
                          genre varchar(20) DEFAULT NULL,
                          gender varchar(7) DEFAULT NULL,
                          year_of_birth date NOT NULL,
                          year_of_death date DEFAULT NULL
);

CREATE TABLE composition (
                       composition_id smallint(6) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       title varchar(40) NOT NULL,
                       year int(4) DEFAULT NULL,
                       musician_id smallint(5) DEFAULT NULL,
                       CONSTRAINT fk_composition FOREIGN KEY (musician_id) REFERENCES musician (musician_id)
);

CREATE TABLE instrument (
    instrument_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE musician_instrument (
    musician_id SMALLINT NOT NULL,
    instrument_id INT NOT NULL,
    PRIMARY KEY (musician_id, instrument_id),
    FOREIGN KEY (musician_id) REFERENCES musician(musician_id),
    FOREIGN KEY (instrument_id) REFERENCES instrument(instrument_id)
);
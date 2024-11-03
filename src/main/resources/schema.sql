DROP DATABASE IF EXISTS musicians;
CREATE DATABASE musicians;
USE musicians;

DROP TABLE IF EXISTS musician;
CREATE TABLE musician (
                          musician_id smallint(6) NOT NULL AUTO_INCREMENT,
                          first_name varchar(20) NOT NULL,
                          last_name varchar(20) DEFAULT NULL,
                          country varchar(15) NOT NULL,
                          genre varchar(20) DEFAULT NULL,
                          gender varchar(7) DEFAULT NULL,
                          year_of_birth date NOT NULL,
                          year_of_death date DEFAULT NULL,
                          PRIMARY KEY (musician_id)
);

DROP TABLE IF EXISTS piece;
CREATE TABLE piece (
                       piece_id smallint(6) NOT NULL AUTO_INCREMENT,
                       title varchar(40) NOT NULL,
                       year int(4) DEFAULT NULL,
                       musician_id smallint(5) DEFAULT NULL,
                       PRIMARY KEY (piece_id),
                       CONSTRAINT fk_piece FOREIGN KEY (musician_id) REFERENCES musician (musician_id)
);
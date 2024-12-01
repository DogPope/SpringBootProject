DROP TABLE IF EXISTS musician_instrument;
DROP TABLE IF EXISTS instrument;
DROP TABLE IF EXISTS composition;
DROP TABLE IF EXISTS musician;

-- The same script, slightly edited to use PostgreSQL instead of MariaDB.
CREATE TABLE musician (
musician_id SERIAL PRIMARY KEY,
first_name varchar(20) NOT NULL,
last_name varchar(20) DEFAULT NULL,
country varchar(15) NOT NULL,
genre varchar(20) DEFAULT NULL,
gender varchar(7) DEFAULT NULL,
year_of_birth DATE NOT NULL, -- Cherry keyboard rules lmao. Why is this note here? And what does it have to do with anything?
year_of_death DATE DEFAULT NULL
);

CREATE TABLE composition (
composition_id SERIAL PRIMARY KEY,
title VARCHAR(40) NOT NULL,
year INTEGER DEFAULT NULL,
musician_id SMALLINT,
CONSTRAINT fk_composition FOREIGN KEY (musician_id) REFERENCES musician (musician_id)
);

CREATE TABLE instrument (
instrument_id SERIAL PRIMARY KEY,
instrument_name VARCHAR(50) NOT NULL
);

CREATE TABLE musician_instrument (
musician_id INT NOT NULL,
instrument_id INT NOT NULL,
PRIMARY KEY (musician_id, instrument_id),
FOREIGN KEY (musician_id) REFERENCES musician(musician_id),
FOREIGN KEY (instrument_id) REFERENCES instrument(instrument_id)
);
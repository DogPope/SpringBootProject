package dev.daniel.compo;

import dev.daniel.compo.instrument.Instrument;
import dev.daniel.compo.musician.*;
import dev.daniel.compo.musician.Musician;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class TestDataUtil {
    public static Musician createMusicianA(){
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Instrument(1,"ACCORDION"));
        return new Musician(
                1,"Jeff","Args", Country.NEPAL, Genre.BLUES, Gender.FEMALE, LocalDate.of(1222,2,11), LocalDate.of(1234,1,1),instruments
        );
    }
    public static Musician createMusicianB(){
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Instrument(1,"ACCORDION"));
        return new Musician(
                2,"Jack","Pils", Country.NORWAY, Genre.POP, Gender.MALE, LocalDate.of(1222,3,11), LocalDate.of(1234,1,1),instruments
        );
    }

    public static Musician createMusicianC() {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Instrument(1,"ACCORDION"));
        return new Musician(
                3,"Jeff","Daniels", Country.ICELAND, Genre.POP, Gender.FEMALE, LocalDate.of(1222,3,11), LocalDate.of(1234,1,4), instruments
        );
    }

    public static Musician createMusicianD() {
        return new Musician(
                "Jeff","Daniels", Country.SWITZERLAND, Genre.CLASSICAL, Gender.MALE, LocalDate.of(1222,6,11), LocalDate.of(1992,4,6)
        );
    }
}

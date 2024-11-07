package dev.daniel.compo;

import dev.daniel.compo.musician.*;
import dev.daniel.compo.musician.Musician;
import dev.daniel.compo.musician.Instrument;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public final class TestDataUtil {
    public static Musician createPianistA(){
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.ACCORDION);
        return new Musician(
                1,"Jeff","Args", Country.NEPAL, Genre.BLUES, Gender.FEMALE, new Date(1222,23,11), new Date(1234,01,01),instruments
        );
    }
    public static Musician createPianistB(){
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.PIANO);
        instruments.add(Instrument.GUITAR);
        return new Musician(
                2,"Jack","Pils", Country.NORWAY, Genre.POP, Gender.MALE, new Date(1222,23,11), new Date(1234,01,01),instruments
        );
    }

    public static Musician createPianistC() {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.ACCORDION);
        instruments.add(Instrument.FLUTE);
        return new Musician(
                3,"Jeff","Daniels", Country.ICELAND, Genre.POP, Gender.FEMALE, new Date(1222,23,11), new Date(1234,01,01), instruments
        );
    }
}

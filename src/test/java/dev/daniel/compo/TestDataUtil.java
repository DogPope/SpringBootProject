package dev.daniel.compo;

import dev.daniel.compo.musician.Composer;
import dev.daniel.compo.musician.Country;
import dev.daniel.compo.musician.Gender;
import dev.daniel.compo.musician.Genre;
import dev.daniel.compo.musician.Pianist;

import java.sql.Date;

public final class TestDataUtil {
    private TestDataUtil(){
    }
    public static Pianist createPianistA(){
        return new Pianist(
                1,"Jeff","Args", Country.NEPAL, Genre.BLUES, Gender.FEMALE, new Date(1222,23,11), new Date(1234,01,01)
        );
    }
    public static Pianist createPianistB(){
        return new Pianist(
                2,"Jack","Pils", Country.NORWAY, Genre.POP, Gender.MALE, new Date(1222,23,11), new Date(1234,01,01)
        );
    }

    public static Pianist createPianistC() {
        return new Pianist(
                3,"Jeff","Daniels", Country.ICELAND, Genre.POP, Gender.FEMALE, new Date(1222,23,11), new Date(1234,01,01)
        );
    }
}

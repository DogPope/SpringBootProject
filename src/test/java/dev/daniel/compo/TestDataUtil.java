package dev.daniel.compo;

import dev.daniel.compo.composer.Composer;
import dev.daniel.compo.composer.Country;
import dev.daniel.compo.composer.Gender;
import dev.daniel.compo.composer.Genre;

import java.sql.Date;

public final class TestDataUtil {
    private TestDataUtil(){
    }
    public static Composer createComposerA(){
        return new Composer(
                1,"Jeff","Args", Country.NEPAL, Genre.BLUES, Gender.FEMALE, new Date(1222,23,11), new Date(1234,01,01)
        );
    }
    public static Composer createComposerB(){
        return new Composer(
                2,"Jack","Pils", Country.NORWAY, Genre.POP, Gender.MALE, new Date(1222,23,11), new Date(1234,01,01)
        );
    }

    public static Composer createComposerC() {
        return new Composer(
                3,"Jeff","Daniels", Country.ICELAND, Genre.POP, Gender.FEMALE, new Date(1222,23,11), new Date(1234,01,01)
        );
    }
}

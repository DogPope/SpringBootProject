package dev.daniel.compo.musician;

import dev.daniel.compo.TestDataUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Just tests for thing like getters and setters.
public class MusicianProgramTests {
    @Test
    public void testID(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals(1,musician1.getMusicianId());
    }
    @Test
    public void testFirstName(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals("Jeff",musician1.getFirstName());
    }
    @Test
    public void testLastName(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals("Args",musician1.getLastName());
    }
    @Test
    public void testCountry(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals(Country.NEPAL,musician1.getCountry());
    }
    @Test
    public void testGenre(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals(Genre.BLUES,musician1.getGenre());
    }
    @Test
    public void testGender(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals(Gender.FEMALE,musician1.getGender());
    }
    @Test
    public void testDateOfBirth(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals(LocalDate.of(1222,2,11),musician1.getDateOfBirth());
    }
    @Test
    public void testDateOfDeath(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals(LocalDate.of(1234,1,1),musician1.getDateOfDeath());
    }
    @Test
    public void testInstruments(){
        Musician musician1 = TestDataUtil.createMusicianA();
        assertEquals(musician1.getInstruments(),musician1.getInstruments());
    }
}

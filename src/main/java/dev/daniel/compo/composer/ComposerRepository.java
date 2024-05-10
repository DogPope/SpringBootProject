package dev.daniel.compo.composer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

@Repository
public class ComposerRepository {
    private ArrayList<Composer> composers = new ArrayList<Composer>();

    ArrayList<Composer> findAll(){
        return composers;
    }
    Optional<Composer> findById(Integer id){
        return composers.stream()
                .filter(composer -> composer.composerId() == id)
                .findFirst();
    }

    void create(Composer composer){
        composers.add(composer);
    }

    void update(Composer composer, Integer id){
        Optional<Composer> existingComposer = findById(id);
        existingComposer.ifPresent(value -> composers.set(composers.indexOf(value), composer));
    }

    void delete(Integer id){
        composers.removeIf(composer -> composer.composerId().equals(id));
    }

    @PostConstruct
    private void init(){
        composers.add(new Composer(
                1,"Ludwig","van Beethoven", Country.GERMANY, Gender.MALE,
                new GregorianCalendar(1770, Calendar.DECEMBER, 17),
                new GregorianCalendar(1827,Calendar.MARCH,26)
        ));
        composers.add(new Composer(
                2,"Claude","deBussy", Country.FRANCE, Gender.MALE,
                new GregorianCalendar(1862, Calendar.AUGUST, 22),
                new GregorianCalendar(1918,Calendar.MARCH,25)
        ));
    }
}

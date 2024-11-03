package dev.daniel.compo.musician;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicianRepository {
    List<Musician> findAll();
    Optional<Musician> findById(Integer id);
    void create(Musician musician);
    void update(Musician musician, Integer id);
    void delete(Integer id);
    int count();
    void saveAll(List<Musician> musician);
}

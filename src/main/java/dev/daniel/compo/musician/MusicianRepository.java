package dev.daniel.compo.musician;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicianRepository {
    List<MusicianFactory> findAll();
    Optional<MusicianFactory> findById(Integer id);
    void create(MusicianFactory musician);
    void update(MusicianFactory musician, Integer id);
    void delete(Integer id);
    int count();
    void saveAll(List<MusicianFactory> musician);
}

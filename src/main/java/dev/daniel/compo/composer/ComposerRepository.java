package dev.daniel.compo.composer;

import java.util.List;
import java.util.Optional;

public interface ComposerRepository {
    List<Composer> findAll();
    Optional<Composer> findComposerById(Integer id);
    void create(Composer composer);
    void update(Composer composer, Integer id);
    void delete(Integer id);
    int count();
    void saveAll(List<Composer> composers);
}

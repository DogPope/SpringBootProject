package dev.daniel.compo.composer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComposerRepository {
    List<Composer> findAll();
    Optional<Composer> findById(Integer id);
    void create(Composer composer);
    void update(Composer composer, Integer id);
    void delete(Integer id);
    int count();
    void saveAll(List<Composer> composers);
}

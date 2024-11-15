package dev.daniel.compo.composition;

import java.util.List;
import java.util.Optional;

public interface CompositionRepository {
    List<Composition> findAll();
    Optional<Composition> findCompositionById(Integer id);
    void create(Composition composition);
    void update(Composition composition, Integer id);
    void delete(Integer id);
    int count();
    void saveAll(List<Composition> composition);
}

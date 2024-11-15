package dev.daniel.compo.instrument;

import java.util.List;
import java.util.Optional;

public interface InstrumentRepository {
    List<Instrument> findAll();
    Optional<Instrument> findInstrumentById(Integer instrumentId);
    void create(Instrument instrument);
    void update(Instrument instrument, Integer instrumentId);
    void delete(Integer id);
    int count();
    void saveAll(List<Instrument> instruments);
}

package dev.daniel.compo.instrument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientInstrumentRepository implements InstrumentRepository{
    private static final Logger log = LoggerFactory.getLogger(JdbcClientInstrumentRepository.class);
    private final JdbcClient jdbcClient;
    private final JdbcTemplate jdbcTemplate;

    public JdbcClientInstrumentRepository(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate){
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Instrument> findAll(){
        return jdbcClient.sql("SELECT * FROM instrument")
                .query(Instrument.class)
                .list();
    }
    public Optional<Instrument> findInstrumentById(Integer instrumentId){
        return jdbcClient.sql("SELECT * FROM instrument WHERE instrument_id=:id")
                .param("id",instrumentId)
                .query(Instrument.class)
                .optional();
    }
    public void create(Instrument instrument){
        log.info(instrument.toString());
        var created = jdbcClient.sql(
                        "INSERT INTO instrument(name, instrument_id) VALUES(?,?)")
                .params(List.of(instrument.getInstrumentName(), instrument.getInstrumentId()))
                .update();
        Assert.state(created == 1, "Failed to create Instrument: " + instrument.getInstrumentName());
    }
    public void update(Instrument instrument, Integer instrumentId){
        jdbcTemplate.update(
                "UPDATE instrument SET name=?, instrument_id=? WHERE instrument_id=?",
                instrument.getInstrumentName(), instrument.getInstrumentId(), instrumentId
        );
        log.info(instrument.toString());
    }
    public void delete(Integer id){
        var deleted = jdbcTemplate.update("DELETE FROM instrument WHERE instrument_id=?", id);
        Assert.state(deleted == 1, "Failed to Delete Instrument: " + id);
    }
    public int count() {
        return jdbcClient.sql("SELECT * FROM instrument").query().listOfRows().size();
    }
    public void saveAll(List<Instrument> instruments){
        instruments.forEach(this::create);
    }
    public static class InstrumentRowMapper implements RowMapper<Instrument> {
        @Override
        public Instrument mapRow(ResultSet rs, int rowNum) throws SQLException {
            Instrument instrument = new Instrument();
            instrument.setInstrumentId(rs.getInt("instrument_id"));
            instrument.setInstrumentName(rs.getString("instrument_name"));
            log.info(instrument.toString());
            return instrument;
        }
    }
}

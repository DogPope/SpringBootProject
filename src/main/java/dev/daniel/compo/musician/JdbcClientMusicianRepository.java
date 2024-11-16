package dev.daniel.compo.musician;
import dev.daniel.compo.instrument.Instrument;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Component
public class JdbcClientMusicianRepository implements MusicianRepository {
    private final JdbcTemplate jdbcTemplate;
    private final JdbcClient jdbcClient;
    public JdbcClientMusicianRepository(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate){
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Musician> findAll() {
        // Good Lord this is complicated. Although it uses a HashMap to improve efficiency, which is great.
        // I'd grade it "F" in terms of complexity. I'd grade it "A" for the fact that it actually works properly.
        String query = """
        SELECT
        m.musician_id, m.first_name, m.last_name, m.country, m.genre,
        m.gender, m.year_of_birth, m.year_of_death,
        i.instrument_id, i.instrument_name
        FROM musician m
        LEFT JOIN musician_instrument mi ON m.musician_id = mi.musician_id
        LEFT JOIN instrument i ON mi.instrument_id = i.instrument_id
        ORDER BY m.musician_id;
        """;

        Map<Integer, Musician> musicianMap = new HashMap<>();

        jdbcTemplate.query(query, (rs) -> {
            int musicianId = rs.getInt("musician_id");

            Musician musician = musicianMap.get(musicianId);
            if (musician == null) {
                musician = new Musician();
                musician.setMusicianId(musicianId);
                musician.setFirstName(rs.getString("first_name"));
                musician.setLastName(rs.getString("last_name"));
                musician.setCountry(Country.valueOf(rs.getString("country")));
                musician.setGenre(Genre.valueOf(rs.getString("genre")));
                musician.setGender(Gender.valueOf(rs.getString("gender")));
                musician.setDateOfBirth(rs.getDate("year_of_birth"));
                musician.setDateOfDeath(rs.getDate("year_of_death"));
                musicianMap.put(musicianId, musician);
            }

            int instrumentId = rs.getInt("instrument_id");
            String instrumentName = rs.getString("instrument_name");
            if (instrumentId > 0) {
                Instrument instrument = new Instrument();
                instrument.setInstrumentId(instrumentId);
                instrument.setInstrumentName(instrumentName);

                musician.getInstruments().add(instrument);
            }
        });
        return new ArrayList<>(musicianMap.values());
    }

    @Override
    public Optional<Musician> findById(Integer id) {
        String query = """
        SELECT
        m.musician_id, m.first_name, m.last_name, m.country, m.genre,
        m.gender, m.year_of_birth, m.year_of_death,
        i.instrument_id, i.instrument_name
        FROM musician m
        LEFT JOIN musician_instrument mi ON m.musician_id = mi.musician_id
        LEFT JOIN instrument i ON mi.instrument_id = i.instrument_id
        WHERE m.musician_id = ?;
        """;

        Map<Integer, Musician> musicianMap = new HashMap<>();

        jdbcTemplate.query(query, (rs) -> {
            int musicianId = rs.getInt("musician_id");

            Musician musician = musicianMap.get(musicianId);
            if (musician == null) {
                musician = new Musician();
                musician.setMusicianId(musicianId);
                musician.setFirstName(rs.getString("first_name"));
                musician.setLastName(rs.getString("last_name"));
                musician.setCountry(Country.valueOf(rs.getString("country")));
                musician.setGenre(Genre.valueOf(rs.getString("genre")));
                musician.setGender(Gender.valueOf(rs.getString("gender")));
                musician.setDateOfBirth(rs.getDate("year_of_birth"));
                musician.setDateOfDeath(rs.getDate("year_of_death"));

                musician.setInstruments(new ArrayList<>());
                musicianMap.put(musicianId, musician);
            }

            int instrumentId = rs.getInt("instrument_id");
            if (instrumentId > 0) {
                Instrument instrument = new Instrument();
                instrument.setInstrumentId(instrumentId);
                instrument.setInstrumentName(rs.getString("instrument_name"));
                musician.getInstruments().add(instrument);
            }
        }, id);

        return musicianMap.values().stream().findFirst();
    }
    public void create(Musician musician){
        jdbcTemplate.update(
                "INSERT INTO musician(first_name, last_name, country, genre, gender, year_of_birth, year_of_death) VALUES(?,?,?,?,?,?,?)",
                musician.getFirstName(),musician.getLastName(),musician.getCountry().toString(),musician.getGenre().toString(),musician.getGender().toString(),musician.getDateOfBirth(), musician.getDateOfDeath());
    }
    public void update(Musician musician, Integer id){
        jdbcTemplate.update("UPDATE musician SET first_name=?,last_name=?,country=?,genre=?,gender=?,year_of_birth=?,year_of_death=? WHERE musician_id=?",
                musician.getFirstName(),musician.getLastName(),musician.getCountry().toString(),musician.getGenre().toString(),musician.getGender().toString(),musician.getDateOfBirth(),musician.getDateOfDeath(), id);
    }
    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM musician WHERE musician_id=?", id);
    }
    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM musician").query().listOfRows().size();
    }
    public void saveAll(List<Musician> musicians){
        musicians.forEach(this::create);
    }
    public static class MusicianRowMapper implements RowMapper<Musician> {
        @Override
        public Musician mapRow(ResultSet rs, int rowNum) throws SQLException {
            Musician musician = new Musician();
            musician.setMusicianId(rs.getInt("musician_id"));
            musician.setFirstName(rs.getString("first_name"));
            musician.setLastName(rs.getString("last_name"));
            musician.setCountry(Country.valueOf(rs.getString("country")));
            musician.setGenre(Genre.valueOf(rs.getString("genre")));
            musician.setGender(Gender.valueOf(rs.getString("gender")));
            musician.setDateOfBirth(rs.getDate("year_of_birth"));
            musician.setDateOfDeath(rs.getDate("year_of_death"));

            return musician;
        }
    }
}

package dev.daniel.compo.musician;

import dev.daniel.compo.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegration {
    @LocalServerPort
    int randomServerPort;

    RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + randomServerPort);
    }

    @Test
    void findAllMusicians() {
        List<MusicianFactory> musicians = restClient.get()
                .uri("/api/musicians")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        assertEquals(7, musicians.size());
    }

    @Test
    void shouldFindRunById() {
        MusicianFactory musician = restClient.get()
                .uri("/api/musicians/1")
                .retrieve()
                .body(MusicianFactory.class);

        assertAll(
                () -> assertEquals(1, musician.getMusicianId()),
                () -> assertEquals("Claude", musician.getFirstName()),
                () -> assertEquals("DeBussy", musician.getLastName()),
                () -> assertEquals("FRANCE", musician.getCountry().toString()),
                () -> assertEquals("CLASSICAL", musician.getGenre().toString()),
                () -> assertEquals("MALE", musician.getGender().toString()),
                () -> assertEquals("1862-08-22", musician.getDateOfBirth().toString()),
                () -> assertEquals("1918-03-25", musician.getDateOfDeath().toString()),
                () -> assertEquals("ACCORDIAN",musician.getInstruments().toString())); // This still needs to be changed.
    }

    @Test
    void createMusician() {
        MusicianFactory musician = TestDataUtil.createPianistA();

        ResponseEntity<Void> newMusician = restClient.post()
                .uri("/api/musicians")
                .body(musician)
                .retrieve()
                .toBodilessEntity();

        assertEquals(201, newMusician.getStatusCodeValue());
    }

    @Test
    void updatedMusician() {
        MusicianFactory musician = restClient.get().uri("/api/musicians/1").retrieve().body(MusicianFactory.class);

        ResponseEntity<Void> updatedMusician = restClient.put()
                .uri("/api/musicians/1")
                .body(musician)
                .retrieve()
                .toBodilessEntity();

        assertEquals(204, updatedMusician.getStatusCodeValue());
    }

    @Test
    void shouldDeleteMusician() {
        ResponseEntity<Void> musician = restClient.delete()
                .uri("/api/musicians/7")
                .retrieve()
                .toBodilessEntity();

        assertEquals(204, musician.getStatusCodeValue());
    }
}

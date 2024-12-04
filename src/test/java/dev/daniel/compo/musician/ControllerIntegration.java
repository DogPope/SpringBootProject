package dev.daniel.compo.musician;

import dev.daniel.compo.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
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
        List<Musician> musicians = restClient.get()
                .uri("/api/musicians")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        assertEquals(7, musicians.size());
    }

    @Test
    void shouldFindMusicianById() {
        MusicianFactory musician = restClient.get()
                .uri("/api/musicians/7")
                .retrieve()
                .body(Musician.class);
                assertEquals(7, musician.getMusicianId());
        System.out.println(musician);
    }

    @Test
    void createMusician() {
        Musician musician = TestDataUtil.createMusicianD();

        ResponseEntity<Void> newMusician = restClient.post()
                .uri("/api/musicians")
                .body(musician)
                .retrieve()
                .toBodilessEntity();

        assertEquals(201, newMusician.getStatusCodeValue());
    }

    @Test
    void updatedMusician() {
        Musician musician = restClient.get().uri("/api/musicians/1").retrieve().body(Musician.class);
        ResponseEntity<Void> updatedMusician = restClient.put()
                .uri("/api/musicians/1")
                .body(musician)
                .retrieve()
                .toBodilessEntity();

        assertEquals(204, updatedMusician.getStatusCodeValue());
    }

    @Test
    void shouldDeleteMusician() {
        // Try to delete someone who doesn't have instruments or compositions associated with them. Gets around foreign key constraints.
        ResponseEntity<Void> musician = restClient.delete()
                .uri("/api/musicians/7")
                .retrieve()
                .toBodilessEntity();

        assertEquals(204, musician.getStatusCodeValue());
    }
}

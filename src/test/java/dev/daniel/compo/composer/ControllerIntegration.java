package dev.daniel.compo.composer;

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
    void findAllComposers() {
        List<Composer> composers = restClient.get()
                .uri("/api/composers")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        assertEquals(7, composers.size());
    }

    @Test
    void shouldFindRunById() {
        Composer composer = restClient.get()
                .uri("/api/composers/1")
                .retrieve()
                .body(Composer.class);

        assertAll(
                () -> assertEquals(1, composer.getComposerId()),
                () -> assertEquals("Claude", composer.getFirstName()),
                () -> assertEquals("DeBussy", composer.getLastName()),
                () -> assertEquals("FRANCE", composer.getCountry().toString()),
                () -> assertEquals("CLASSICAL", composer.getGenre().toString()),
                () -> assertEquals("MALE", composer.getGender().toString()),
                () -> assertEquals("1862-08-22", composer.getDateOfBirth().toString()),
                () -> assertEquals("1918-03-25", composer.getDateOfDeath().toString()));
    }

    @Test
    void createComposer() {
        Composer composer = TestDataUtil.createComposerA();

        ResponseEntity<Void> newComposer = restClient.post()
                .uri("/api/composers")
                .body(composer)
                .retrieve()
                .toBodilessEntity();

        assertEquals(201, newComposer.getStatusCodeValue());
    }

    @Test
    void updatedComposer() {
        Composer composer = restClient.get().uri("/api/composers/1").retrieve().body(Composer.class);

        ResponseEntity<Void> updatedComposer = restClient.put()
                .uri("/api/composers/1")
                .body(composer)
                .retrieve()
                .toBodilessEntity();

        assertEquals(204, updatedComposer.getStatusCodeValue());
    }

    @Test
    void shouldDeleteComposer() {
        ResponseEntity<Void> composer = restClient.delete()
                .uri("/api/composers/7")
                .retrieve()
                .toBodilessEntity();

        assertEquals(204, composer.getStatusCodeValue());
    }
}

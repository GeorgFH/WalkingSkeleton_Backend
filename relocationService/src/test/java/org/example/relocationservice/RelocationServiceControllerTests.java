package org.example.relocationservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RelocationServiceControllerTests {

    private WebTestClient webTestClient;


    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Test
    void testHealthEndpoint() {
        webTestClient.get()
                .uri("/health")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(String.class)
                .isEqualTo("Service is healthy");
    }

    @Test
    void testSubmitFormData() {
        String requestBody = """
            {
                "moveDate": "2025-03-21",
                "name": "Max Mustermann",
                "from": "Musterstraße 1",
                "to": "Hauptstraße 2",
                "amount": 10,
                "fromFloor": 3,
                "fromElevatorAvailable": true,
                "toFloor": 2,
                "toElevatorAvailable": false,
                "phoneNumber": "+491234567890"
            }
        """;

        webTestClient.post()
                .uri("/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(String.class)
                .isEqualTo("Formular erfolgreich empfangen");
    }


    @Test
    void testFormDataSavedToDatabase() {
        String requestBody = """
            {
                "moveDate": "2025-03-21",
                "name": "Max Mustermann",
                "from": "Musterstraße 1",
                "to": "Hauptstraße 2",
                "amount": 10,
                "fromFloor": 3,
                "fromElevatorAvailable": true,
                "toFloor": 2,
                "toElevatorAvailable": false
            }
        """;

        webTestClient.post()
                .uri("/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);

        webTestClient.get()
                .uri("/requests")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(String.class)
                .value(response -> {
                    if (!response.contains("Max Mustermann")) {
                        throw new AssertionError("Daten wurden nicht in der Datenbank gespeichert.");
                    }
                });
    }
}

package org.example.relocationservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RelocationServiceControllerTests {


    @Autowired
    private RelocationRequestRepository repository;

    private WebTestClient webTestClient;


    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
        repository.deleteAll();
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
                "moveDate": "2025-03-22",
                "name": "Max Mustermann",
                "from": "Musterstraße 1, 12345 Musterstadt",
                "to": "Beispielstraße 2, 98765 Beispielstadt",
                "amount": 5,
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
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Formular erfolgreich empfangen");

        assert repository.count() > 0;
    }


    @Test
    void testGetAllRequests() {

        String requestBody = """
            {
                "moveDate": "2025-03-24",
                "name": "Georg Mustermann",
                "from": "Musterstraße 1, 12345 Musterstadt",
                "to": "Beispielstraße 2, 98765 Beispielstadt",
                "amount": 5,
                "fromFloor": 3,
                "fromElevatorAvailable": true,
                "toFloor": 2,
                "toElevatorAvailable": false
            }
        """;

        webTestClient.post()
                .uri("/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);

        // Test the /requests endpoint
        webTestClient.get()
                .uri("/requests")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBodyList(RelocationRequest.class)
                .hasSize(1);  // expects at least one request in the database
    }


}

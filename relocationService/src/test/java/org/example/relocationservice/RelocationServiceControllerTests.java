package org.example.relocationservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
                "name": "Max Mustermann",
                "address": "Musterstraße 1, 12345 Musterstadt",
                "email": "max.mustermann@email.com",
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
                .isEqualTo("Formular erfolgreich empfangen");  // Erwartete Antwort
    }

}

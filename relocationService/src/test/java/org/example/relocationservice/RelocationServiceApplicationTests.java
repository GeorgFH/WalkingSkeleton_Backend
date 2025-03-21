package org.example.relocationservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class RelocationServiceApplicationTests {

    @LocalServerPort
    private int port;

    private final WebTestClient webTestClient = WebTestClient.bindToServer()
            .baseUrl("http://localhost:" + port)
            .build();

    @Test
    void testHealthEndpointNotFound() {
        webTestClient.get()
                .uri("/health")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);  // Wir erwarten einen Fehler 404
    }
}

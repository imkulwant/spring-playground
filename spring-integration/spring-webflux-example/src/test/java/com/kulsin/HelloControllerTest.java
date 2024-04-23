package com.kulsin;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(HelloController.class)
class HelloControllerTest {


    @InjectMocks
    private HelloController helloController;

    @Test
    public void testSayHello() {
        WebTestClient
                .bindToController(helloController)
                .build()
                .get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello, Spring WebFlux!");
    }

}
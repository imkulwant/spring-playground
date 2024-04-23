package com.kulsin.circuitbreaker;

import com.kulsin.AppConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Import(value = {AppConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CircuitBreakerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private DownClient client;

    @Test
    void fetch() throws InterruptedException {
        when(client.getRandomResponse())
                .thenThrow(new RuntimeException("Simulated error"))
                .thenThrow(new RuntimeException("Simulated error"))
                .thenThrow(new RuntimeException("Simulated error"))
                .thenThrow(new RuntimeException("Simulated error"))
                .thenThrow(new RuntimeException("Simulated error"))
                .thenReturn("Success");

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity("/fetch", String.class);
                    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
                });

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity("/fetch", String.class);
                    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, responseEntity.getStatusCode());
                });

        verify(client, Mockito.times(5)).getRandomResponse();

        Thread.sleep(5000);

        ResponseEntity<String> response = restTemplate.getForEntity("/fetch", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());

    }
}
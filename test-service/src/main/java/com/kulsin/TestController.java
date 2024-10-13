package com.kulsin;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private final RestClient restClient;

    public TestController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/api")
    public void test() {

        restClient.post()
                .uri("/dummy")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("value1", "value2"))
                .retrieve()
                .body(String.class);

    }

}

package com.kulsin.circuitbreaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CircuitBreakerController {

    private int callCounter = 0;
    @Autowired
    private DownClient downClient;

    @GetMapping("/fetch")
    @CircuitBreaker(name = "fetchCircuitBreaker")
    public ResponseEntity<String> fetch() {
        log.error("Call number {}", ++callCounter);
        return ResponseEntity.ok().body(downClient.getRandomResponse());
    }

}

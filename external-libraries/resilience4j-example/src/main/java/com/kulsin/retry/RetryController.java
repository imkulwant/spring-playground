package com.kulsin.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RetryController {

    @Autowired
    private UnstableClient client;

    @GetMapping("/retry")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok().body(client.unstableWithRetry());
    }

}

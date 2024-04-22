package com.kulsin.retry;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class UnstableClient {

    private static final String UNSTABLE_SERVICE = "unstableService";

    @Retry(name = UNSTABLE_SERVICE)
    public String unstableWithRetry() {
        return getRandomResponse();
    }

    private String getRandomResponse() {
        Random random = new Random();
        int num = random.nextInt();
        log.info(">>>> {}", num);
        int o = num % 2;
        log.info(">>>> {}", o);

        if (o == 1)
            return "success";

        log.error("Unexpected error call failed!");
        throw new RuntimeException("Unexpected error");

    }

}
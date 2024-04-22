package com.kulsin.circuitbreaker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class DownClient {

    public String getRandomResponse() {
        Random random = new Random();
        int num = random.nextInt();
        int o = num % 2;
        if (o == 1)
            return "success";

        log.error("Unexpected error call failed!");
        throw new RuntimeException("Unexpected error");
    }

}
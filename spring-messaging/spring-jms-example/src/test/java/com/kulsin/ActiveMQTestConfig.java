package com.kulsin;

import jakarta.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ActiveMQTestConfig {
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("test-queue");
    }
}

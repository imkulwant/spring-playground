package com.kulsin;

import com.kulsin.support.logging.HttpTraceResponseInterceptor;
import com.kulsin.support.restclient.RestClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class TestAppConfig {

    @Bean
    public RestClient restClient(HttpTraceResponseInterceptor httpTraceResponseInterceptor) {
        return RestClientFactory
                .createRestClient("http://localhost:9090",
                        Duration.ofSeconds(10),
                        Duration.ofSeconds(10),
                        httpTraceResponseInterceptor
                );
    }

}

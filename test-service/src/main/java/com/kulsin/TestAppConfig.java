package com.kulsin;

import com.kulsin.support.restclient.RestClientFactory;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class TestAppConfig {

    @Bean
    public RestClient testRestClient(HttpClientBuilder httpClientBuilder) {
        return RestClientFactory
                .createRestClient("http://localhost:9090", httpClientBuilder
                );
    }

}

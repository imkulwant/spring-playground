package com.kulsin.support.config;

import com.kulsin.support.restclient.RestClientFactory;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public HttpClientBuilder httpClientBuilder() {
        return RestClientFactory.httpClientBuilder();
    }

    @Bean
    public RestClient restClient(HttpClientBuilder httpClientBuilder) {
        return RestClientFactory.createRestClient(httpClientBuilder);
    }

}

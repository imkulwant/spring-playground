package com.kulsin.support.restclient;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

public class RestClientFactory {

    public static RestClient createRestClient(String baseUrl, Duration connectTimeout, Duration readTimeout) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(createRequestFactory(connectTimeout, readTimeout))
                .build();

    }

    public static RestClient createRestClient(Duration connectTimeout, Duration readTimeout) {
        return RestClient.builder()
                .requestFactory(createRequestFactory(connectTimeout, readTimeout))
                .build();

    }

    private static ClientHttpRequestFactory createRequestFactory(Duration connectTimeout, Duration readTimeout) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

}

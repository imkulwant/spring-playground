package com.kulsin.support.restclient;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

public class RestClientFactory {

    private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(5);

    public static HttpClientBuilder httpClientBuilder() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.of(DEFAULT_CONNECT_TIMEOUT))
                .setResponseTimeout(Timeout.of(DEFAULT_READ_TIMEOUT))
                .build();

        return HttpClientBuilder
                .create()
                .setDefaultRequestConfig(requestConfig);
    }

    public static RestClient createRestClient(HttpClientBuilder httpClientBuilder) {
        return RestClient.builder()
                .requestFactory(httpRequestFactory(httpClientBuilder))
                .build();

    }

    public static RestClient createRestClient(String baseUrl, HttpClientBuilder httpClientBuilder) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(httpRequestFactory(httpClientBuilder))
                .build();

    }

    private static ClientHttpRequestFactory createRequestFactory(Duration connectTimeout, Duration readTimeout) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

    private static HttpComponentsClientHttpRequestFactory httpRequestFactory(HttpClientBuilder httpClientBuilder) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClientBuilder.build());
        return factory;
    }
}

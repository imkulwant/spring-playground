package com.kulsin.support.restclient;

import com.kulsin.support.logging.HttpTraceResponseInterceptor;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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

    public static RestClient createRestClient(String baseUrl, Duration connectTimeout, Duration readTimeout, HttpTraceResponseInterceptor httpTraceResponseInterceptor) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(httpComponentsClientHttpRequestFactory(httpTraceResponseInterceptor))
                .build();

    }

    private static ClientHttpRequestFactory createRequestFactory(Duration connectTimeout, Duration readTimeout) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

    private static HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(HttpTraceResponseInterceptor httpTraceResponseInterceptor) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(HttpClients.custom()
                .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                        .build())
                .addResponseInterceptorLast(httpTraceResponseInterceptor)
                .build());

        return factory;
    }
}

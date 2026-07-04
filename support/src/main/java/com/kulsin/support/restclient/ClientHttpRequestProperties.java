package com.kulsin.support.restclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@EnableConfigurationProperties
@ConfigurationProperties("com.kulsin.client.http")
public class ClientHttpRequestProperties {

    private String url;
    public Duration connectTimeoutInSeconds = Duration.ofSeconds(5);
    public Duration readTimeoutInSeconds = Duration.ofSeconds(30);

}

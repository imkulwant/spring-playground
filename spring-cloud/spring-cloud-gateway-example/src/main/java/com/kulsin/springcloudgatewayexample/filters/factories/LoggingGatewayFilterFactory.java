package com.kulsin.springcloudgatewayexample.filters.factories;

import com.kulsin.springcloudgatewayexample.filters.factories.LoggingGatewayFilterFactory.Config;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Factory class for creating a logging gateway filter.
 * This class extends AbstractGatewayFilterFactory to provide filter creation functionality.
 */
@Slf4j
@Component
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<Config> {

    public LoggingGatewayFilterFactory() {
        super(Config.class);
    }


    /**
     * Creates a gateway filter based on the provided configuration.
     *
     * @param config Configuration for the logging gateway filter.
     * @return GatewayFilter instance for logging.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Pre-processing
            if (config.isPreLogger()) {
                log.info("Pre GatewayFilter logging: {}", config.getBaseMessage());
            }

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // Post-processing
                        log.info("Post GatewayFilter logging: {}", config.getBaseMessage());
                    }));
        };
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config {

        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

    }

}

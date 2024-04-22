package com.kulsin.springcloudgatewayexample.config;

import com.kulsin.springcloudgatewayexample.filters.factories.LoggingGatewayFilterFactory;
import com.kulsin.springcloudgatewayexample.filters.factories.LoggingGatewayFilterFactory.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * Configuration class for setting up Gateway configuration.
 * This class defines beans for configuring global filters and routes for the Gateway.
 */
@Slf4j
@Configuration
public class GatewayConfiguration {

    /**
     * Creates a global filter for post-processing requests.
     * This filter logs a message after processing the request.
     *
     * @return GlobalFilter instance for post-processing.
     */
    @Bean
    public GlobalFilter postGlobalFilter() {
        return ((exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        log.info("Global Post Filter Executed!!!");
                    }));
        });
    }

    /**
     * Configures routes for the Gateway.
     * This method defines routes based on incoming paths and applies filters accordingly.
     *
     * @param builder        RouteLocatorBuilder for building routes.
     * @param loggingFactory LoggingGatewayFilterFactory for creating logging filters.
     * @return RouteLocator instance representing configured routes.
     */
    @Bean
    public RouteLocator routes(
            RouteLocatorBuilder builder,
            LoggingGatewayFilterFactory loggingFactory
    ) {
        return builder.routes()
                .route("generate_route_java_config", r -> r.path("/generate/**")
                        .filters(f -> f.rewritePath("/generate(?<segment>/?.*)", "$\\{segment}")
                                .filter(loggingFactory.apply(new Config("Custom Message for Generate", true, true))))
                        .uri("http://localhost:8081"))
                .build();
    }

}

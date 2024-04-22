package com.kulsin.springcloudgatewayexample.filters.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingGlobalPreFilter implements GlobalFilter {

    /**
     * Filters the incoming request and logs a message indicating the execution of the "pre" logic.
     * This filter is executed as part of the global filter chain before the request reaches its target handler.
     *
     * @param exchange ServerWebExchange containing the request and response objects.
     * @param chain    GatewayFilterChain for continuing the filter chain execution.
     * @return Mono<Void> representing completion of the filtering process.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global Pre Filter executed!");
        return chain.filter(exchange);
    }

}

package com.kulsin.springcloudgatewayexample.filters.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Global filter responsible for demonstrating the behavior of filters within a Spring filter chain.
 * This filter exemplifies how filters are ordered and executed, both before and after the target handler.
 * It showcases the precedence and sequencing of filters in the filter chain.
 * <p>
 * In Spring, configuring a filter chain involves defining a sequence of filters that process incoming requests
 * before they reach the target handler (e.g., a controller in a web application). Filters can perform operations
 * both before and after the request is processed by the target handler.
 * <p>
 * The behavior described here pertains to the precedence of filters in the filter chain. Filters are ordered based
 * on their precedence, with those having lower precedence executing earlier in the chain compared to those with higher precedence.
 * <p>
 * Precedence (Order in the Chain): Filters are ordered based on their precedence. Filters with lower precedence have
 * a lower order in the chain, leading to their execution earlier in the sequence compared to those with higher precedence.
 * <p>
 * Pre-Execution Logic: Upon receiving a request, filters with lower precedence execute their "pre" logic early in the processing
 * pipeline. This typically includes tasks like request preprocessing, authentication, or logging. Consequently, a filter with lower
 * precedence performs its pre-processing steps before those with higher precedence.
 * <p>
 * Post-Execution Invocation: After the request has been processed by the target handler and is returning from there, filters
 * execute their "post" logic. Despite executing earlier in the pipeline, filters with lower precedence have their "post" implementation
 * invoked later in the sequence compared to those with higher precedence. This ensures that, although their "pre" logic runs early,
 * their "post" logic runs after that of filters with higher precedence.
 * <p>
 * In summary, the behavior described ensures that filters in a Spring filter chain operate in a predictable sequence based on their
 * precedence, facilitating proper request processing and response handling.
 */
@Slf4j
@Component
public class FirstPreLastPostGlobalFilter implements GlobalFilter, Ordered {

    /**
     * Filters the incoming request and logs a message indicating the execution of the "pre" logic.
     * This filter is configured to execute as the first in the sequence of global filters.
     *
     * @param exchange ServerWebExchange containing the request and response objects.
     * @param chain    GatewayFilterChain for continuing the filter chain execution.
     * @return Mono<Void> representing completion of the filtering process.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("First Pre Global Filter");

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Last Post Global Filter");
        }));

    }

    /**
     * Retrieves the order of this filter in the filter chain.
     * This filter is configured with the lowest precedence to ensure it executes first.
     *
     * @return Order of this filter in the filter chain.
     */
    @Override
    public int getOrder() {
        return -1;
    }
}

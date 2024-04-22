package com.kulsin.springcloudgatewayexample.filters.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulsin.springcloudgatewayexample.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter.ROUTE_TO_URL_FILTER_ORDER;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

/**
 * Global filter responsible for dynamically modifying the route of incoming requests based on certain conditions.
 * This filter intercepts requests and adjusts their routes dynamically before forwarding them to the appropriate destination.
 */
@Slf4j
@Component
public class DynamicRouteGlobalFilter implements GlobalFilter, Ordered {

    /**
     * Retrieves the order of this filter in the filter chain.
     * The order is set to be executed after the RouteToUrlFilter, ensuring proper routing handling.
     *
     * @return Order of this filter in the filter chain.
     */
    @Override
    public int getOrder() {
        return ROUTE_TO_URL_FILTER_ORDER + 1;
    }

    /**
     * Filters the incoming request and dynamically modifies its route based on predefined conditions.
     *
     * @param exchange ServerWebExchange containing the request and response objects.
     * @param chain    GatewayFilterChain for continuing the filter chain execution.
     * @return Mono<Void> representing completion of the filtering process.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {

            // Retrieve the route information from the exchange
            Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);

            // Check if the request path is "/resource" and the route ID is "dynamic_service_route"
            if ("/resource".equals(exchange.getRequest().getPath().toString())
                    && route != null && "dynamic_service_route".equals(route.getId())
            ) {
                // Extract the cached request body and deserialize it into TransactionRequest object
                Object cachedBody = exchange.getAttribute(ServerWebExchangeUtils.CACHED_REQUEST_BODY_ATTR);

                ObjectMapper objectMapper = new ObjectMapper();
                String plainBody = objectMapper.writeValueAsString(cachedBody);
                TransactionRequest transactionRequest = objectMapper.readValue(plainBody, TransactionRequest.class);

                // Determine the destination host based on the country code in the request metadata
                String host;
                if ("UK".equals(transactionRequest.getMetadata().getCountryCode())) {
                    host = "http://localhost:8081/";
                } else {
                    host = "http://localhost:8082/";
                }

                // Update the request URI with the new host
                URI uri = (URI) exchange.getAttributes().get(GATEWAY_REQUEST_URL_ATTR);
                log.info("GATEWAY_REQUEST_URL_ATTR uri={} before", uri);
                URI updatedUri = URI.create(host + uri.getPath());
                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, updatedUri);
                log.info("GATEWAY_REQUEST_URL_ATTR uri={} after", updatedUri);

                return chain.filter(exchange.mutate().build());

            }

        } catch (Exception ex) {
            log.error("Exception occurred while applying routing gateway filter!", ex);
        }
        return chain.filter(exchange);

    }

}

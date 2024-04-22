package com.kulsin.springcloudgatewayexample.filters.factories;

import com.kulsin.springcloudgatewayexample.filters.factories.ModifyRequestGatewayFilterFactory.Config;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Factory class for creating a Gateway Filter responsible for modifying incoming requests.
 * This filter aims to enhance the incoming request handling by adjusting the Accept-Language header and
 * stripping off query parameters from the request URI, ensuring consistency and improved processing.
 * The modification behavior can be customized through configuration.
 */
@Slf4j
@Component
public class ModifyRequestGatewayFilterFactory extends AbstractGatewayFilterFactory<Config> {

    public ModifyRequestGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * Applies the modify request filter to the incoming request based on the provided configuration.
     * If the Accept-Language header is empty, it populates it with the default locale specified in the configuration.
     * Additionally, it removes all query parameters from the request URI to ensure cleaner and more predictable routing.
     *
     * @param config Configuration for the modify request filter.
     * @return GatewayFilter instance for modifying incoming requests.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            // Check if Accept-Language header is empty
            if (exchange.getRequest().getHeaders().getAcceptLanguage().isEmpty()) {
                log.info("AcceptLanguage header is empty");

                // Get locale from query parameter or use default locale from config
                String queryParamLocale = exchange.getRequest().getQueryParams().getFirst("locale");
                Locale requestLocale = Optional.ofNullable(queryParamLocale).map(Locale::forLanguageTag).orElse(config.getDefaultLocale());

                // Set Accept-Language header with request locale
                exchange.getRequest().mutate().headers(h -> h.setAcceptLanguageAsLocales(List.of(requestLocale)));
            }

            // Log Accept-Language header contents
            String allOutgoingRequestLanguages = exchange.getRequest().getHeaders().getAcceptLanguage()
                    .stream().map(LanguageRange::getRange)
                    .collect(Collectors.joining(","));

            log.info("Modify request output - Request contains Accept-Language header: {}", allOutgoingRequestLanguages);

            // Modify request URI by removing query parameters
            ServerWebExchange modifiedServerWebExchange = exchange.mutate()
                    .request(originalRequest ->
                            originalRequest.uri(UriComponentsBuilder.fromUri(exchange.getRequest().getURI())
                                    .replaceQueryParams(new LinkedMultiValueMap<String, String>())
                                    .build()
                                    .toUri()
                            )
                    )
                    .build();

            // Log the modified request URI
            log.info("Removed all query params: {}", modifiedServerWebExchange.getRequest().getURI());

            // Continue the filter chain with the modified exchange
            return chain.filter(modifiedServerWebExchange);
        };
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config {

        private Locale defaultLocale;

    }

}

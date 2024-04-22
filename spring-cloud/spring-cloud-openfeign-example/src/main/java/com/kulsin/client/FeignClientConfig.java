package com.kulsin.client;

import com.kulsin.errorhandling.CustomErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class FeignClientConfig {

    /*
     * There are four logging levels to choose from:
     *     NONE – no logging, which is the default
     *     BASIC – log only the request method, URL and response status
     *     HEADERS – log the basic information together with request and response headers
     *     FULL – log the body, headers and metadata for both request and response
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    /*
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("user", "admin");
            requestTemplate.header("password", "admin");
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
        };
    }
    */

    /*
    To add the interceptor to the request chain, add the bean to our Configuration class or,
    declare it in the properties file:

    feign:
        client:
            config:
                default:
                    requestInterceptors:
                        com.baeldung.cloud.openfeign.JSONPlaceHolderInterceptor

    Alternatively, we can use the BasicAuthRequestInterceptor class that the Spring Cloud OpenFeign provides:

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("username", "password");
    }
    */

    /*
        Feign client is composed of a set of customizable components.

        Spring Cloud creates a new default set on demand for each named client using the FeignClientsConfiguration class that we can customize.

        FeignClientsConfiguration contains these beans:

        Decoder – ResponseEntityDecoder, which wraps SpringDecoder, used to decode the Response
        Encoder – SpringEncoder is used to encode the RequestBody.
        Logger – Slf4jLogger is the default logger used by Feign.
        Contract – SpringMvcContract, which provides annotation processing
        Feign-Builder – HystrixFeign.Builder is used to construct the components.
        Client – LoadBalancerFeignClient or default Feign client
*/

}

package com.kulsin.support.config;

import com.kulsin.support.logging.HttpTransactionResponseInterceptor;
import com.kulsin.support.logging.HttpTransactionLogGenerator;
import com.kulsin.support.logging.LoggingOutboundHttpCallListener;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
@EnableConfigurationProperties(HttpTransactionLogProperties.class)
public class HttpTransactionLogConfig {

    @Bean
    HttpTransactionResponseInterceptor httpTransactionResponseInterceptor(HttpTransactionLogProperties configProps,
                                                                          LoggingOutboundHttpCallListener outboundHttpTraceListener) {
        return new HttpTransactionResponseInterceptor(configProps, outboundHttpTraceListener);
    }

    @Bean
    LoggingOutboundHttpCallListener outboundHttpCallListener(HttpTransactionLogGenerator httpTransactionLogGenerator) {
        return new LoggingOutboundHttpCallListener(httpTransactionLogGenerator);
    }

    @Bean
    HttpTransactionLogGenerator httpTransactionLogGenerator(HttpTransactionLogProperties configProps) {
        return new HttpTransactionLogGenerator(configProps);
    }

    @Bean
    public static BeanPostProcessor apacheHttpClientTraceInterceptorInjector(ObjectProvider<HttpTransactionResponseInterceptor> httpTraceResponseInterceptorProvider) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
                if (bean instanceof HttpClientBuilder builder) {
                    httpTraceResponseInterceptorProvider.ifAvailable(
                            builder::addResponseInterceptorFirst
                    );
                }
                return bean;
            }
        };
    }


}

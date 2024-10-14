package com.kulsin.support.config;

import com.kulsin.support.logging.HttpTraceLogConfigProps;
import com.kulsin.support.logging.HttpTraceResponseInterceptor;
import com.kulsin.support.logging.HttpTraceEntryStringRenderer;
import com.kulsin.support.logging.LoggingOutboundHttpTraceListener;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
@EnableConfigurationProperties(HttpTraceLogConfigProps.class)
public class HttpLoggingConfig {

    @Bean
    HttpTraceResponseInterceptor httpTraceResponseInterceptor(HttpTraceLogConfigProps configProps,
                                                              LoggingOutboundHttpTraceListener outboundHttpTraceListener) {
        return new HttpTraceResponseInterceptor(configProps, outboundHttpTraceListener);
    }

    @Bean
    LoggingOutboundHttpTraceListener outboundHttpTraceListener(HttpTraceEntryStringRenderer httpTraceEntryStringRenderer) {
        return new LoggingOutboundHttpTraceListener(httpTraceEntryStringRenderer);
    }

    @Bean
    HttpTraceEntryStringRenderer httpTraceEntryStringRenderer(HttpTraceLogConfigProps configProps) {
        return new HttpTraceEntryStringRenderer(configProps);
    }

    @Bean
    public static BeanPostProcessor apacheHttpClientTraceInterceptorInjector(ObjectProvider<HttpTraceResponseInterceptor> httpTraceResponseInterceptorProvider) {
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

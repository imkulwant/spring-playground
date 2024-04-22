package com.kulsin;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringJPAMySQLExample {

    public static void main(String[] args) {
        SpringApplication.run(SpringJPAMySQLExample.class, args);
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog("classpath:liquibase-change-log.xml");
        springLiquibase.setDataSource(dataSource);
        return springLiquibase;
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

}

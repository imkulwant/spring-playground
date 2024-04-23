package com.kulsin.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kulsin.string_type_message")
public class SpringKafkaExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaExampleApplication.class, args);
	}

}

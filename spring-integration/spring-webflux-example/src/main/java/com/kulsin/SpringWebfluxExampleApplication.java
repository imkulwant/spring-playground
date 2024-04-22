package com.kulsin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class SpringWebfluxExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxExampleApplication.class, args);
	}

}

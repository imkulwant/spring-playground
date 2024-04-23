package com.kulsin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.in.security", "com.in.rest","com.in.service", "com.in.repository"})
public class MongoDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDataServiceApplication.class, args);
	}

}

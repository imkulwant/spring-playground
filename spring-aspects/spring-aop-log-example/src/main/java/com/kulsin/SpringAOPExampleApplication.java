package com.kulsin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LoggingAspect.class)
@EnableAspectJAutoProxy
public class SpringAOPExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAOPExampleApplication.class, args);
	}

}

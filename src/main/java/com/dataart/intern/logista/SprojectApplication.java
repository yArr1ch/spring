package com.dataart.intern.logista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprojectApplication.class, args);

	}
}

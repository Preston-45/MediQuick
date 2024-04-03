package com.tibuhealth.Tibu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TibuApplication {
	public TibuApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(TibuApplication.class, args);
	}

	@GetMapping({"count-users"})
	public String getCounterUser() {
		return "Hello Render with Spring Boot";
	}
}
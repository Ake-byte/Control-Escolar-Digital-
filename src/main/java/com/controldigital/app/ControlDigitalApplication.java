package com.controldigital.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase principal del sistema.
 */
@SpringBootApplication
@EnableScheduling
public class ControlDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlDigitalApplication.class, args);
	}

}

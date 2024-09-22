package com.example.backend_go2climb_iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BackendGo2ClimbIoTApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendGo2ClimbIoTApplication.class, args);
	}

}

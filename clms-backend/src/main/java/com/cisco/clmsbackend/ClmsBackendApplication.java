package com.cisco.clmsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.cisco.clmsbackend.repository")
@EnableJpaAuditing
public class ClmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClmsBackendApplication.class, args);
	}
}

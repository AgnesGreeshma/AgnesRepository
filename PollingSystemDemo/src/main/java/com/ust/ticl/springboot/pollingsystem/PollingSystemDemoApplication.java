package com.ust.ticl.springboot.pollingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.ust.ticl.springboot.pollingsystem")
@SpringBootApplication
@EnableJpaAuditing
public class PollingSystemDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollingSystemDemoApplication.class, args);
	}

}

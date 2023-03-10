package com.example.checkers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EntityScan(basePackages = {"com.example.checkers.checkers.models.entities"})
public class CheckersApplication {
	public static void main(String[] args) {
		SpringApplication.run(CheckersApplication.class, args);
//		ApplicationContext context = SpringApplication.run(CheckersApplication.class, args);
	}
}

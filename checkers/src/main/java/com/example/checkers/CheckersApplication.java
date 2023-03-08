package com.example.checkers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan({"com.example.checkers.checkers.bussiness; com.example.checkers.checkers.controllers; com.example.checkers"})
@EnableAutoConfiguration
public class CheckersApplication {
	public static void main(String[] args) {
		SpringApplication.run(CheckersApplication.class, args);
//		ApplicationContext context = SpringApplication.run(CheckersApplication.class, args);
	}
}

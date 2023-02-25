package com.example.checkers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.checkers.checkers.bussiness"})
public class CheckersApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CheckersApplication.class, args);

	}
}

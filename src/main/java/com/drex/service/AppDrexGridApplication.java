package com.drex.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class AppDrexGridApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppDrexGridApplication.class, args);
	}

}

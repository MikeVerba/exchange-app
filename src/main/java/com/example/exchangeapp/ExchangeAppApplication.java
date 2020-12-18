package com.example.exchangeapp;

import com.example.exchangeapp.config.ApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ApiProperties.class)
@SpringBootApplication
public class ExchangeAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExchangeAppApplication.class, args);
	}
}

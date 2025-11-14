package com.example.main;

import com.example.main.model.Product;
import com.example.main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MainApplication {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public static void main(String[] args) {

		SpringApplication.run(MainApplication.class, args);
	}
}

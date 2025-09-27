package com.maxo99.microservices.product_service;

import org.springframework.boot.SpringApplication;

import com.maxo99.microservices.product.ProductServiceApplication;

public class TestProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

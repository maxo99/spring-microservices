package com.maxo99.mciroservices.order;

import org.springframework.boot.SpringApplication;

public class TestOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

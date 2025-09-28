package com.maxo99.microservices.order;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.3.0"));

	@LocalServerPort
	private Integer randomPort;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = randomPort;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldCreateOrder() {
		String requestBody = """
				{
				  "skuCode": "ABC123",
				  "price": 100.00,
				  "quantity": 2
				}
				""";
		RestAssured.given()
				.header("Content-Type", "application/json")
				.body(requestBody)
				.when().post("/api/order")
				.then().statusCode(201)
				.body(Matchers.equalTo("Order Placed Successfully"));
	}

}

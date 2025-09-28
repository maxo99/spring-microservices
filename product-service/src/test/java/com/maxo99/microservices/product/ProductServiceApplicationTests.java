package com.maxo99.microservices.product;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer randomPort;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = randomPort;
	}

	static {
		mongoDBContainer.start();

	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				    "name": "test-product",
				    "description": "sample description",
				    "price": 999.99
				}
				""";
		RestAssured.given()
				.header("Content-Type", "application/json")
				.body(requestBody)
				.when().post("/api/product")
				.then().statusCode(201)
				.body("id", Matchers.notNullValue());
	}

}

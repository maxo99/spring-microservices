package com.maxo99.microservices.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import io.restassured.RestAssured;

// @Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.3.0"));
	

	
	@LocalServerPort
	private int port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}
	static {
		mysqlContainer.start();
	}


	@Test
	void checkAvailability() {
		// Check is true when quantity is available
		RestAssured.given()
			.queryParam("skuCode", "iphone_13")
			.queryParam("quantity", 50)
			.when()
			.get("/api/inventory")
			.then()
			.statusCode(200)
			.assertThat()
			.body(org.hamcrest.Matchers.equalTo("true"));

		// Check is false when quantity is not available
		RestAssured.given()
			.queryParam("skuCode", "iphone_13")
			.queryParam("quantity", 500)
			.when()
			.get("/api/inventory")
			.then()
			.statusCode(200)
			.assertThat()
			.body(org.hamcrest.Matchers.equalTo("false"));

	}

}

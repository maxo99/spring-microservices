package com.maxo99.microservices.order.stubs;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class InventoryClientStub {
    
    public static void stubCheckAvailability(String skuCode, int quantity) {
        stubFor(get(urlPathEqualTo("/api/inventory"))
            .withQueryParam("skuCode", equalTo(skuCode))
            .withQueryParam("quantity", equalTo(String.valueOf(quantity)))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("true"))); // or "false" based on your test case
        // This method will be implemented by Spring Cloud Contract WireMock
        // It will stub the /api/inventory endpoint to return the desired availability
    }
}

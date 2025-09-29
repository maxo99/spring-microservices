package com.maxo99.microservices.order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange()
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackDefault")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);



    default boolean fallbackDefault(String skuCode, Integer quantity, Throwable throwable) {
        log.warn("Inventory service is down. Fallback method called for SKU: {} Failure Reason: {}", skuCode, throwable.getMessage());
        return false;
    }
}

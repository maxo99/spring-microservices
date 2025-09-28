package com.maxo99.microservices.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory", url = "http://localhost:8082/api/inventory")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET)
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
    
}

package com.maxo99.microservices.order.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maxo99.microservices.order.dto.OrderRequest;
import com.maxo99.microservices.order.model.Order;
import com.maxo99.microservices.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        orderRepository.save(order);
    }

}

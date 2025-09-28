package com.maxo99.microservices.order.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maxo99.microservices.order.client.InventoryClient;
import com.maxo99.microservices.order.dto.OrderRequest;
import com.maxo99.microservices.order.dto.OrderResponse;
import com.maxo99.microservices.order.model.Order;
import com.maxo99.microservices.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (!isProductInStock) {
            throw new RuntimeException(
                    "Product with sku:" + orderRequest.skuCode() + " is not in stock, please try again later.");
        }
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        orderRepository.save(order);
    }

    public OrderResponse getOrder(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));
        return new OrderResponse(order.getOrderNumber(), "SUCCESS");
    }

    public List<OrderResponse> getAllOrders() {
        var orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderResponse(order.getOrderNumber(), "SUCCESS"))
                .toList();
    }

}

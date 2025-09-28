package com.maxo99.microservices.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxo99.microservices.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

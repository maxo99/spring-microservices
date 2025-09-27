package com.maxo99.microservices.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maxo99.microservices.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

} 

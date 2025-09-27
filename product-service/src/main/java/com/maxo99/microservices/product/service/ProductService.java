package com.maxo99.microservices.product.service;

import org.springframework.stereotype.Service;

import com.maxo99.microservices.product.dto.ProductRequest;
import com.maxo99.microservices.product.model.Product;
import com.maxo99.microservices.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest request) {

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }
}

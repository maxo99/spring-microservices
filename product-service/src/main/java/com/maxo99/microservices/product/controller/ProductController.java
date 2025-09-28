package com.maxo99.microservices.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maxo99.microservices.product.dto.ProductIdResponse;
import com.maxo99.microservices.product.dto.ProductRequest;
import com.maxo99.microservices.product.dto.ProductResponse;
import com.maxo99.microservices.product.model.Product;
import com.maxo99.microservices.product.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductIdResponse createProduct(@RequestBody ProductRequest productRequest) {
        String product_id =  productService.createProduct(productRequest);
        return new ProductIdResponse(product_id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

}
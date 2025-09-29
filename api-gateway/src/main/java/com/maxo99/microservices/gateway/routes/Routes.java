package com.maxo99.microservices.gateway.routes;

import java.net.URI;

import org.springframework.cloud.gateway.server.mvc.common.MvcUtils;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {
    

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
        .route(RequestPredicates.path("/api/product"), request -> {
            MvcUtils.setRequestUrl(request, URI.create("http://localhost:8080"));
            return HandlerFunctions.http().handle(request);
        })
        .build();     
    }


    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order_service")
        .route(RequestPredicates.path("/api/order"), request -> {
            MvcUtils.setRequestUrl(request, URI.create("http://localhost:8081"));
            return HandlerFunctions.http().handle(request);
        })
        .build();     
    }


    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions.route("inventory_service")
        .route(RequestPredicates.path("/api/inventory"), request -> {
            MvcUtils.setRequestUrl(request, URI.create("http://localhost:8082"));
            return HandlerFunctions.http().handle(request);
        })
        .build();     
    }

}

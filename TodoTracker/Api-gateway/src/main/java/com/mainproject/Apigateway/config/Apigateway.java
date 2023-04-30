package com.mainproject.Apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Apigateway {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(r->r
                        .path("/todo/v1/**")
                        .uri("http://user-auth-service:9002"))
                .route(r->r
                        .path("/todo/v2/**")
                        .uri("http://todolist-service:9001"))
                .build();
    }
}

package com.backend.orders.domain.services;

import com.backend.orders.domain.model.aggregates.Product;
import com.backend.orders.domain.model.commands.CreateProductCommand;
import com.backend.orders.domain.model.queries.GetAllProductsQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService {
    Mono<Product> handle(CreateProductCommand command);
    Flux<Product> handle(GetAllProductsQuery query);
}

package com.backend.orders.domain.services;

import com.backend.orders.domain.model.aggregates.Product;
import com.backend.orders.domain.model.commands.CreateProductCommand;
import com.backend.orders.domain.model.queries.GetAllProductsQuery;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> handle(CreateProductCommand command);
    List<Product> handle(GetAllProductsQuery query);
}

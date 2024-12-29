package com.backend.orders.domain.model.commands;

import com.backend.orders.domain.model.aggregates.Product;
import com.backend.orders.domain.model.entities.OrderProduct;
import reactor.core.publisher.Flux;

import java.util.List;

public record CreateOrderCommand(
        Long customerId,
        List<OrderProduct> products
) {
    public CreateOrderCommand{
        if (customerId == null) {
            throw new IllegalArgumentException("customerId is required");
        }
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("products is required");
        }

    }
}

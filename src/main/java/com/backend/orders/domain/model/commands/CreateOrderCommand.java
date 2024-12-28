package com.backend.orders.domain.model.commands;

import com.backend.orders.domain.model.aggregates.Product;
import reactor.core.publisher.Flux;

import java.util.List;

public record CreateOrderCommand(
        String customerId,
        Flux<Product> products,
        double total
) {
    public CreateOrderCommand{
        if(customerId==null || customerId.isBlank()){
            throw new IllegalArgumentException("customerId cannot be null or empty");
        }
        if (products == null) {
            throw new IllegalArgumentException("products cannot be null");
        }
       if (total <= 0) {
            throw new IllegalArgumentException("total must be greater than 0");
        }
    }
}

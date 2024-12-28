package com.backend.orders.domain.model.commands;

import com.backend.orders.domain.model.aggregates.Product;
import reactor.core.publisher.Flux;

public record UpdateOrderCommand(
        String orderId,
        String customerId,
        Flux<Product> products,
        double total
) {
    public UpdateOrderCommand{
        if(orderId==null || orderId.isBlank()){
            throw new IllegalArgumentException("orderId cannot be null or empty");
        }
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

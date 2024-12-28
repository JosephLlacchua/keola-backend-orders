package com.backend.orders.domain.model.commands;

public record CreateOrderCommand(
        Long customerId,
        Long productId,
        Integer quantity
) {
}

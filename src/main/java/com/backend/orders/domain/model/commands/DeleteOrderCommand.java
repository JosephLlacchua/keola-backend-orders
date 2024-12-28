package com.backend.orders.domain.model.commands;

public record DeleteOrderCommand(
        Long orderId
) {
    public DeleteOrderCommand {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("orderId cannot be null or less than or equal to 0");
        }
    }
}

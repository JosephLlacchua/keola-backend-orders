package com.backend.orders.domain.model.commands;

public record DeleteOrderCommand(
        String orderId
) {
    public DeleteOrderCommand {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId cannot be null or empty");
        }
    }
}

package com.backend.orders.domain.model.queries;

public record GetOrderByIdQuery(
        String orderId
) {
    public GetOrderByIdQuery {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId cannot be null or empty");
        }
    }
}

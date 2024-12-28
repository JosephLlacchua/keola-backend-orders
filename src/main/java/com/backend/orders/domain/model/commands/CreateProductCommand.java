package com.backend.orders.domain.model.commands;

public record CreateProductCommand(
        String name,
        String description,
        Double price,
        Integer stock
) {
}

package com.backend.orders.domain.model.commands;

public record CreateProductCommand(
        String name,
        String description,
        Double price,
        Integer stock
) {
    public CreateProductCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Stock cannot be null or negative");
        }
    }
}

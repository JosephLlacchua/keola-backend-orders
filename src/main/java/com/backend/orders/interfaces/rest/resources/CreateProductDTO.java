package com.backend.orders.interfaces.rest.resources;

public record CreateProductDTO(
        String name,
        String description,
        Double price,
        Integer stock
) {
}

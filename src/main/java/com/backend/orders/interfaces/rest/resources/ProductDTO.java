package com.backend.orders.interfaces.rest.resources;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock
) {
}

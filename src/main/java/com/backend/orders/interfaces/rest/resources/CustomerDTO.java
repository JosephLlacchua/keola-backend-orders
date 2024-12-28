package com.backend.orders.interfaces.rest.resources;

public record CustomerDTO(
        Long id,
        String name,
        String email,
        String phone,
        String address
) {
}

package com.backend.orders.interfaces.rest.resources;

public record CreateCustomerDTO(
        String name,
        String email,
        String phone,
        String address
) {
}

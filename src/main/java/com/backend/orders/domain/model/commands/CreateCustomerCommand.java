package com.backend.orders.domain.model.commands;

public record CreateCustomerCommand(
        String name,
        String email,
        String phone,
        String address
) {
}

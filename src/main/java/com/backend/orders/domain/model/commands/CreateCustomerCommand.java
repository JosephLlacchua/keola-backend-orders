package com.backend.orders.domain.model.commands;

public record CreateCustomerCommand(
        String name,
        String email,
        String phone,
        String address
) {
    public CreateCustomerCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
    }
}

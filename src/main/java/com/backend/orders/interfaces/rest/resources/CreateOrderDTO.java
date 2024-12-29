package com.backend.orders.interfaces.rest.resources;

import com.backend.orders.domain.model.entities.OrderProduct;

import java.util.List;

public record CreateOrderDTO(
        Long customerId,
        List<OrderProduct> products
) {
}
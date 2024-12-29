package com.backend.orders.interfaces.rest.transform;

import com.backend.orders.domain.model.aggregates.Order;
import com.backend.orders.interfaces.rest.resources.OrderDTO;

public class OrderDtoFromEntityAssembler {
    public static OrderDTO toDtoFromEntity(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomerId(),
                order.getProducts(),
                order.getTotal()
        );
    }
}

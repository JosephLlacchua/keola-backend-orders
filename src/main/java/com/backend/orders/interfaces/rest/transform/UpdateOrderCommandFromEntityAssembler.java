package com.backend.orders.interfaces.rest.transform;

import com.backend.orders.domain.model.commands.UpdateOrderCommand;
import com.backend.orders.interfaces.rest.resources.UpdateOrderDTO;

public class UpdateOrderCommandFromEntityAssembler {
    public static UpdateOrderCommand toCommandFromDto(String orderId, UpdateOrderDTO dto) {
        return new UpdateOrderCommand(
                orderId,
                dto.customerId(),
                dto.products()
        );
    }
}
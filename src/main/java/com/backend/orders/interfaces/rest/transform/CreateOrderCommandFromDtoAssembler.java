package com.backend.orders.interfaces.rest.transform;

import com.backend.orders.domain.model.commands.CreateOrderCommand;
import com.backend.orders.interfaces.rest.resources.CreateOrderDTO;

public class CreateOrderCommandFromDtoAssembler {
    public static CreateOrderCommand toCommandFromDto(CreateOrderDTO dto) {
        return new CreateOrderCommand(
                dto.customerId(),
                dto.products()
        );
    }
}

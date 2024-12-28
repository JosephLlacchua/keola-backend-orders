package com.backend.orders.interfaces.rest.transform;

import com.backend.orders.domain.model.commands.CreateProductCommand;
import com.backend.orders.interfaces.rest.resources.CreateProductDTO;

public class CreateProductCommandFromDtoAssembler {
    public static CreateProductCommand toCommandFromDto(CreateProductDTO dto) {
        return new CreateProductCommand(
                dto.name(),
                dto.description(),
                dto.price(),
                dto.stock()
        );
    }
}

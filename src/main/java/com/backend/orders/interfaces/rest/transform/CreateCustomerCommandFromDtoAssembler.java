package com.backend.orders.interfaces.rest.transform;

import com.backend.orders.domain.model.commands.CreateCustomerCommand;
import com.backend.orders.interfaces.rest.resources.CreateCustomerDTO;

public class CreateCustomerCommandFromDtoAssembler {
    public static CreateCustomerCommand toCommandFromDto(CreateCustomerDTO dto) {
        return new CreateCustomerCommand(
                dto.name(),
                dto.email(),
                dto.phone(),
                dto.address()
        );
    }

}

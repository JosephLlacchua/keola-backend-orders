package com.backend.orders.interfaces.rest.transform;

import com.backend.orders.domain.model.aggregates.Customer;
import com.backend.orders.interfaces.rest.resources.CustomerDTO;

public class CustomerDtoFromEntityAssembler {
    public static CustomerDTO toDtoFromEntity(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}

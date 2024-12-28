package com.backend.orders.domain.services;

import com.backend.orders.domain.model.aggregates.Customer;
import com.backend.orders.domain.model.commands.CreateCustomerCommand;
import com.backend.orders.domain.model.queries.GetAllCustomersQuery;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> handle(CreateCustomerCommand command);
    List<Customer> handle(GetAllCustomersQuery query);
}

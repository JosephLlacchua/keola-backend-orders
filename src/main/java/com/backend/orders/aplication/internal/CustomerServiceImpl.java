package com.backend.orders.aplication.internal;

import com.backend.orders.domain.model.aggregates.Customer;
import com.backend.orders.domain.model.commands.CreateCustomerCommand;
import com.backend.orders.domain.model.queries.GetAllCustomersQuery;
import com.backend.orders.domain.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Optional<Customer> handle(CreateCustomerCommand command) {
        return Optional.empty();
    }

    @Override
    public List<Customer> handle(GetAllCustomersQuery query) {
        return List.of();
    }
}

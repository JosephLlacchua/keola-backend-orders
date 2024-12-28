package com.backend.orders.domain.services;

import com.backend.orders.domain.model.aggregates.Customer;
import com.backend.orders.domain.model.commands.CreateCustomerCommand;
import com.backend.orders.domain.model.queries.GetAllCustomersQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CustomerService {
    Mono<Customer> handle(CreateCustomerCommand command);
    Flux<Customer> handle(GetAllCustomersQuery query);
}

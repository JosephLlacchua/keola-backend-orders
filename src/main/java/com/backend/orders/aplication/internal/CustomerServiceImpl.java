package com.backend.orders.aplication.internal;

import com.backend.orders.domain.model.aggregates.Customer;
import com.backend.orders.domain.model.commands.CreateCustomerCommand;
import com.backend.orders.domain.model.queries.GetAllCustomersQuery;
import com.backend.orders.domain.services.CustomerService;
import com.backend.orders.infrastructure.persistence.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Mono<Customer> handle(CreateCustomerCommand command) {
        return customerRepository.existsByEmail(command.email())
                .flatMap(existsByEmail -> {
                    if (existsByEmail) {
                        return Mono.error(new IllegalArgumentException("Email already exists: " + command.email()));
                    }
                    return customerRepository.existsByPhone(command.phone());
                })
                .flatMap(existsByPhone -> {
                    if (existsByPhone) {
                        return Mono.error(new IllegalArgumentException("Phone number already exists: " + command.phone()));
                    }
                    var customer = new Customer(command);
                    return customerRepository.save(customer);
                });
    }

    @Override
    public Flux<Customer> handle(GetAllCustomersQuery query) {
        return customerRepository.findAll();
    }
}

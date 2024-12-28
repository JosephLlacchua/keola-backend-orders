package com.backend.orders.infrastructure.persistence.repositories;

import com.backend.orders.domain.model.aggregates.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
    Mono<Boolean> existsByEmail(String email);
    Mono<Boolean> existsByPhone(String phone);
}

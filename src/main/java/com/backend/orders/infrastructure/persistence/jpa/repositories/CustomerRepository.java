package com.backend.orders.infrastructure.persistence.jpa.repositories;

import com.backend.orders.domain.model.aggregates.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
    Mono<Boolean> existsByEmail(String email);
    Mono<Boolean> existsByPhone(String phone);
}

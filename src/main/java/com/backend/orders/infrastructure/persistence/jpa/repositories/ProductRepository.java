package com.backend.orders.infrastructure.persistence.jpa.repositories;

import com.backend.orders.domain.model.aggregates.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Long> {
    Mono<Boolean> existsByName(String name);
}

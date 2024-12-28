package com.backend.orders.infrastructure.persistence.repositories;

import com.backend.orders.domain.model.aggregates.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends R2dbcRepository<Product,Long> {
    Mono<Boolean> existsByName(String name);
}

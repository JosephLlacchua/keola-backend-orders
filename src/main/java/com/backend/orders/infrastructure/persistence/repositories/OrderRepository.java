package com.backend.orders.infrastructure.persistence.repositories;

import com.backend.orders.domain.model.aggregates.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
}

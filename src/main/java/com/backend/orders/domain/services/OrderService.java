package com.backend.orders.domain.services;

import com.backend.orders.domain.model.aggregates.Order;
import com.backend.orders.domain.model.commands.CreateOrderCommand;
import com.backend.orders.domain.model.commands.DeleteOrderCommand;
import com.backend.orders.domain.model.commands.UpdateOrderCommand;
import com.backend.orders.domain.model.queries.GetAllOrdersQuery;
import com.backend.orders.domain.model.queries.GetOrderByIdQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



public interface OrderService {
    Mono<Order> handle(CreateOrderCommand command);
    Mono<Order> handle(UpdateOrderCommand command);
    Mono<Void> handle(DeleteOrderCommand command);

    Mono<Order> handle(GetOrderByIdQuery query);
    Flux<Order> handle(GetAllOrdersQuery query);
}

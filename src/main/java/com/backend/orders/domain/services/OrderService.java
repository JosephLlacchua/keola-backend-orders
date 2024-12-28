package com.backend.orders.domain.services;

import com.backend.orders.domain.model.aggregates.Order;
import com.backend.orders.domain.model.commands.CreateOrderCommand;
import com.backend.orders.domain.model.commands.DeleteOrderCommand;
import com.backend.orders.domain.model.commands.UpdateOrderCommand;
import com.backend.orders.domain.model.queries.GetAllOrdersQuery;
import com.backend.orders.domain.model.queries.GetOrderByIdQuery;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> handle(CreateOrderCommand command);
    Optional<Order> handle(UpdateOrderCommand command);
    void handle(DeleteOrderCommand command);

    Optional<Order> handle(GetOrderByIdQuery query);
    List<Order> handle(GetAllOrdersQuery query);
}

package com.backend.orders.aplication.internal;

import com.backend.orders.domain.model.aggregates.Order;
import com.backend.orders.domain.model.commands.CreateOrderCommand;
import com.backend.orders.domain.model.commands.DeleteOrderCommand;
import com.backend.orders.domain.model.commands.UpdateOrderCommand;
import com.backend.orders.domain.model.queries.GetAllOrdersQuery;
import com.backend.orders.domain.model.queries.GetOrderByIdQuery;
import com.backend.orders.domain.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Optional<Order> handle(CreateOrderCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> handle(UpdateOrderCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteOrderCommand command) {

    }

    @Override
    public Optional<Order> handle(GetOrderByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Order> handle(GetAllOrdersQuery query) {
        return List.of();
    }
}

package com.backend.orders.aplication.internal;

import com.backend.orders.domain.model.aggregates.Order;
import com.backend.orders.domain.model.aggregates.Product;
import com.backend.orders.domain.model.commands.CreateOrderCommand;
import com.backend.orders.domain.model.commands.DeleteOrderCommand;
import com.backend.orders.domain.model.commands.UpdateOrderCommand;
import com.backend.orders.domain.model.queries.GetAllOrdersQuery;
import com.backend.orders.domain.model.queries.GetOrderByIdQuery;
import com.backend.orders.domain.services.OrderService;
import com.backend.orders.infrastructure.persistence.repositories.CustomerRepository;
import com.backend.orders.infrastructure.persistence.repositories.OrderRepository;
import com.backend.orders.infrastructure.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Order> handle(CreateOrderCommand command) {
        return customerRepository.findById(Long.valueOf(command.customerId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Customer not found: " + command.customerId())))
                .flatMap(customer -> {
                    Flux<Product> products = command.products()
                            .flatMap(product -> productRepository.findById(product.getId())
                                    .switchIfEmpty(Mono.error(new IllegalArgumentException("Product not found: " + product.getId()))));
                    return products.collectList()
                            .flatMap(productList -> {
                                Order order = new Order(command);
                                return orderRepository.save(order);
                            });
                });
    }

    @Override
    public Mono<Order> handle(UpdateOrderCommand command) {
        return orderRepository.findById(command.orderId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Order not found: " + command.orderId())))
                .flatMap(order -> {
                    Flux<Product> products = command.products()
                            .flatMap(product -> productRepository.findById(product.getId())
                                    .switchIfEmpty(Mono.error(new IllegalArgumentException("Product not found: " + product.getId()))));
                    return products.collectList()
                            .flatMap(productList -> {
                                order.updateInformation(command.customerId(), command.products(), command.total());
                                return orderRepository.save(order);
                            });
                });
    }

    @Override
    public Mono<Void> handle(DeleteOrderCommand command) {
        return orderRepository.findById(command.orderId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Order not found: " + command.orderId())))
                .flatMap(orderRepository::delete);
    }

    @Override
    public Mono<Order> handle(GetOrderByIdQuery query) {
        return orderRepository.findById(query.orderId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Order not found: " + query.orderId())));
    }

    @Override
    public Flux<Order> handle(GetAllOrdersQuery query) {
        return orderRepository.findAll();
    }
}
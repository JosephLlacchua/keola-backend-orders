package com.backend.orders.aplication.internal;

import com.backend.orders.domain.model.aggregates.Order;
import com.backend.orders.domain.model.aggregates.Product;
import com.backend.orders.domain.model.commands.CreateOrderCommand;
import com.backend.orders.domain.model.commands.DeleteOrderCommand;
import com.backend.orders.domain.model.commands.UpdateOrderCommand;
import com.backend.orders.domain.model.entities.OrderProduct;
import com.backend.orders.domain.model.queries.GetAllOrdersQuery;
import com.backend.orders.domain.model.queries.GetOrderByIdQuery;
import com.backend.orders.domain.services.OrderService;
import com.backend.orders.infrastructure.persistence.repositories.CustomerRepository;
import com.backend.orders.infrastructure.persistence.repositories.OrderRepository;
import com.backend.orders.infrastructure.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
        return customerRepository.findById(command.customerId())
                .switchIfEmpty(Mono.error(new RuntimeException("Cliente no encontrado")))
                .flatMap(customer -> {
                    return Flux.fromIterable(command.products())
                            .flatMap(orderProduct -> productRepository.findById(orderProduct.getProductId())
                                    .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado: " + orderProduct.getProductId())))
                                    .flatMap(product -> {
                                        if (product.getStock() < orderProduct.getQuantity()) {
                                            return Mono.error(new RuntimeException("Stock insuficiente para el producto: " + orderProduct.getProductId()));
                                        }
                                        return Mono.just(product);
                                    })
                            )
                            .collectList()
                            .flatMap(products -> {
                                // Calcular el total
                                double total = calculateTotal(products, command.products());
                                Order order = new Order(command, total);
                                return orderRepository.save(order);
                            });
                });
    }

    @Override
    public Mono<Order> handle(UpdateOrderCommand command) {
        return orderRepository.findById(command.orderId())
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
                .flatMap(existingOrder -> {
                    existingOrder.setProducts(command.products());
                    List<OrderProduct> orderProducts = command.products();
                    return Flux.fromIterable(orderProducts)
                            .flatMap(orderProduct -> productRepository.findById(orderProduct.getProductId())
                                    .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado: " + orderProduct.getProductId())))
                            )
                            .collectList()
                            .flatMap(products -> {
                                double total = calculateTotal(products, orderProducts);
                                existingOrder.setTotal(total);
                                return orderRepository.save(existingOrder);
                            });
                });
    }

    @Override
    public Mono<Void> handle(DeleteOrderCommand command) {
        return orderRepository.findById(command.orderId())
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
                .flatMap(orderRepository::delete);
    }

    @Override
    public Mono<Order> handle(GetOrderByIdQuery query) {
        return orderRepository.findById(query.orderId())
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")));
    }

    @Override
    public Flux<Order> handle(GetAllOrdersQuery query) {
        return orderRepository.findAll();
    }

    // Método para calcular el total basado en los productos y sus cantidades
    private double calculateTotal(List<Product> products, List<OrderProduct> orderProducts) {
        double total = 0.0;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            OrderProduct orderProduct = orderProducts.get(i);
            total += product.getPrice() * orderProduct.getQuantity();
        }
        return total;
    }
}


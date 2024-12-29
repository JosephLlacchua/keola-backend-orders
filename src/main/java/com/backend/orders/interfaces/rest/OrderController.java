package com.backend.orders.interfaces.rest;


import com.backend.orders.domain.model.commands.CreateOrderCommand;
import com.backend.orders.domain.model.commands.UpdateOrderCommand;
import com.backend.orders.domain.model.commands.DeleteOrderCommand;
import com.backend.orders.domain.model.queries.GetAllOrdersQuery;
import com.backend.orders.domain.model.queries.GetOrderByIdQuery;
import com.backend.orders.domain.services.OrderService;
import com.backend.orders.interfaces.rest.resources.CreateOrderDTO;
import com.backend.orders.interfaces.rest.resources.UpdateOrderDTO;
import com.backend.orders.interfaces.rest.resources.OrderDTO;
import com.backend.orders.interfaces.rest.transform.CreateOrderCommandFromDtoAssembler;
import com.backend.orders.interfaces.rest.transform.OrderDtoFromEntityAssembler;
import com.backend.orders.interfaces.rest.transform.UpdateOrderCommandFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "All order related endpoints")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Create a new Order")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OrderDTO> createOrder(@RequestBody CreateOrderDTO dto) {
        CreateOrderCommand command = CreateOrderCommandFromDtoAssembler.toCommandFromDto(dto);
        return orderService.handle(command)
                .map(OrderDtoFromEntityAssembler::toDtoFromEntity);
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Update an existing Order")
    public Mono<OrderDTO> updateOrder(
            @PathVariable String orderId,
            @RequestBody UpdateOrderDTO dto
    ) {
        UpdateOrderCommand command = UpdateOrderCommandFromEntityAssembler.toCommandFromDto(orderId, dto);
        return orderService.handle(command)
                .map(OrderDtoFromEntityAssembler::toDtoFromEntity);
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "Delete an existing Order")
    public Mono<ResponseEntity<?>> deleteOrder(@PathVariable String orderId) {
        var deleteOrderCommand = new DeleteOrderCommand(orderId);
        return orderService.handle(deleteOrderCommand)
                .then(Mono.just(ResponseEntity.ok("Order with given id successfully deleted")));
    }



    @GetMapping("/{orderId}")
    @Operation(summary = "Get an Order by its ID")
    public Mono<OrderDTO> getOrderById(@PathVariable String orderId) {
        GetOrderByIdQuery query = new GetOrderByIdQuery(orderId);
        return orderService.handle(query)
                .map(OrderDtoFromEntityAssembler::toDtoFromEntity);
    }

    @GetMapping
    @Operation(summary = "Get all Orders")
    public Flux<OrderDTO> getAllOrders() {
        GetAllOrdersQuery query = new GetAllOrdersQuery();
        return orderService.handle(query)
                .map(OrderDtoFromEntityAssembler::toDtoFromEntity);
    }
}

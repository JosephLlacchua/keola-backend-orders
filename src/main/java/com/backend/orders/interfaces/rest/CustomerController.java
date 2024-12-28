package com.backend.orders.interfaces.rest;

import com.backend.orders.domain.model.queries.GetAllCustomersQuery;
import com.backend.orders.domain.services.CustomerService;
import com.backend.orders.interfaces.rest.resources.CreateCustomerDTO;
import com.backend.orders.interfaces.rest.resources.CustomerDTO;
import com.backend.orders.interfaces.rest.transform.CreateCustomerCommandFromDtoAssembler;
import com.backend.orders.interfaces.rest.transform.CustomerDtoFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "The Customer API")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Create a new customer")
    public Mono<ResponseEntity<CustomerDTO>> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        var createDtoCommand = CreateCustomerCommandFromDtoAssembler.toCommandFromDto(createCustomerDTO);
        return customerService.handle(createDtoCommand)
                .map(CustomerDtoFromEntityAssembler::toDtoFromEntity)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping
    @Operation(summary = "Get all customers")
    public Flux<CustomerDTO> getAllCustomers() {
        return customerService.handle(new GetAllCustomersQuery())
                .map(CustomerDtoFromEntityAssembler::toDtoFromEntity)
                .onErrorResume(e -> Flux.empty());
    }
}
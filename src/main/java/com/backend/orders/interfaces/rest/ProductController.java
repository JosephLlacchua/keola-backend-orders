package com.backend.orders.interfaces.rest;


import com.backend.orders.domain.model.queries.GetAllProductsQuery;
import com.backend.orders.domain.services.ProductService;
import com.backend.orders.interfaces.rest.resources.CreateProductDTO;
import com.backend.orders.interfaces.rest.resources.ProductDTO;
import com.backend.orders.interfaces.rest.transform.CreateProductCommandFromDtoAssembler;
import com.backend.orders.interfaces.rest.transform.ProductDtoFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product", description = "The Product API")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public Flux<ProductDTO> getAllProducts() {
        return productService.handle(new GetAllProductsQuery())
                .map(ProductDtoFromEntityAssembler::toDtoFromEntity)
                .onErrorResume(e -> Flux.empty());
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public Mono<ResponseEntity<ProductDTO>> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        var createDtoCommand = CreateProductCommandFromDtoAssembler.toCommandFromDto(createProductDTO);
        return productService.handle(createDtoCommand)
                .map(ProductDtoFromEntityAssembler::toDtoFromEntity)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }
}

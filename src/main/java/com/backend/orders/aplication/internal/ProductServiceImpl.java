package com.backend.orders.aplication.internal;

import com.backend.orders.domain.model.aggregates.Product;
import com.backend.orders.domain.model.commands.CreateProductCommand;
import com.backend.orders.domain.model.queries.GetAllProductsQuery;
import com.backend.orders.domain.services.ProductService;
import com.backend.orders.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Product> handle(CreateProductCommand command) {
        return productRepository.existsByName(command.name())
                .flatMap(existsByName -> {
                    if (existsByName) {
                        return Mono.error(new IllegalArgumentException("Product already exists: " + command.name()));
                    }
                    var product = new Product(command);
                    return productRepository.save(product);
                });
    }

    @Override
    public Flux<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }
}

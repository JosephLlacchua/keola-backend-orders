package com.backend.orders.aplication.internal;

import com.backend.orders.domain.model.aggregates.Product;
import com.backend.orders.domain.model.commands.CreateProductCommand;
import com.backend.orders.domain.model.queries.GetAllProductsQuery;
import com.backend.orders.domain.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Optional<Product> handle(CreateProductCommand command) {
        return Optional.empty();
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return List.of();
    }
}

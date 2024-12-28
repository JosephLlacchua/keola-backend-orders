package com.backend.orders.interfaces.rest.transform;

import com.backend.orders.domain.model.aggregates.Product;
import com.backend.orders.interfaces.rest.resources.ProductDTO;

public class ProductDtoFromEntityAssembler {
    public static ProductDTO toDtoFromEntity(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}

package com.backend.orders.domain.model.aggregates;

import com.backend.orders.domain.model.commands.CreateProductCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("products")
public class Product {

    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer stock;

    @CreatedDate
    private LocalDateTime  createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Product(CreateProductCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.stock = command.stock();
    }
}

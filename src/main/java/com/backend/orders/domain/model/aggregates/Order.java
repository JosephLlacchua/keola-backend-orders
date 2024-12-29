package com.backend.orders.domain.model.aggregates;

import com.backend.orders.domain.model.commands.CreateOrderCommand;
import com.backend.orders.domain.model.entities.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private Long customerId;

    private List<OrderProduct> products;

    private Double total;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public Order(CreateOrderCommand command ,double total) {
        this.customerId = command.customerId();
        this.products = command.products();
        this.total = total;
    }


}

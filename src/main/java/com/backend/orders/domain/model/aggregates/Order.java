package com.backend.orders.domain.model.aggregates;

import com.backend.orders.domain.model.commands.CreateOrderCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Flux;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String customer;

    private Flux<Product> products;

    private Double total;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Order(CreateOrderCommand command) {
        this.customer = command.customerId();
        this.products = command.products();
        this.total = command.total();
    }

    public Order updateInformation(String customer, Flux<Product> products, double total) {
        this.customer = customer;
        this.products = products;
        this.total = total;
        return this;
    }
}

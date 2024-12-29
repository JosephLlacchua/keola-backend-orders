package com.backend.orders.domain.model.aggregates;


import com.backend.orders.domain.model.commands.CreateCustomerCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "customers")
public class Customer {

    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @Size(max =9)
    private String phone;

    private String address;

    @CreatedDate
    private LocalDateTime  createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


    public Customer(CreateCustomerCommand command) {
        this.name = command.name();
        this.email = command.email();
        this.phone = command.phone();
        this.address = command.address();
    }
}

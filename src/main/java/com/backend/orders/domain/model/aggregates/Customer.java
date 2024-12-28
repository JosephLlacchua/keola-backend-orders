package com.backend.orders.domain.model.aggregates;


import com.backend.orders.domain.model.commands.CreateCustomerCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @Size(max =9)
    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Customer(CreateCustomerCommand command) {
        this.name = command.name();
        this.email = command.email();
        this.phone = command.phone();
        this.address = command.address();
    }
}

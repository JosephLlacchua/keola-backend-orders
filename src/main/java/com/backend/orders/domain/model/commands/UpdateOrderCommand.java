package com.backend.orders.domain.model.commands;

public record UpdateOrderCommand(
        Long orderId,
        Long customerId,
        Long productId,
        Integer quantity
) {
    public UpdateOrderCommand{
        if(orderId==null || orderId<=0){
            throw new IllegalArgumentException("orderId cannot be null or less than or equal to 0");
        }
        if(customerId==null || customerId<=0){
            throw new IllegalArgumentException("customerId cannot be null or less than or equal to 0");
        }
        if(productId==null || productId<=0){
            throw new IllegalArgumentException("productId cannot be null or less than or equal to 0");
        }
        if(quantity==null || quantity<=0){
            throw new IllegalArgumentException("quantity cannot be null or less than or equal to 0");
        }

    }
}

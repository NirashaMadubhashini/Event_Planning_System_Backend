package com.spring.eventPlaning.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    public Long orderId;
    public List<PlaceOrder> placeOrders;
    public String status;
    public Long totalPrice;
}

package com.spring.eventPlaning.dto;

import com.spring.eventPlaning.entity.PlaceOrder;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    public Long orderId;
    public List<PlaceOrder> placeOrders;
    public String status;
    public Long totalPrice;
}

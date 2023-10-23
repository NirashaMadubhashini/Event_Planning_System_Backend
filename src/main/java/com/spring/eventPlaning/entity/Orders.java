package com.spring.eventPlaning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<PlaceOrder> placeOrders;
    private String status;
    private Long totalPrice;

}

package com.spring.eventPlaning.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PlaceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    public String serviceType;
    public String vendorId;
    public String packageId;
    public Long price;
}

package com.spring.eventPlaning.dto;

import lombok.Data;

@Data
public class PlaceOrderDTO {
    public String serviceType;
    public String vendorId;
    public String packageId;
    public Long price;
}

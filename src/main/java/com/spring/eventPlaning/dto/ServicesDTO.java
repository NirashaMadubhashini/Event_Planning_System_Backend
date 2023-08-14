package com.spring.eventPlaning.dto;

import lombok.Data;

@Data
public class ServicesDTO {
    private Long serviceId;
    private String serviceName;
    private String description;
    private Long servicePrice;
}

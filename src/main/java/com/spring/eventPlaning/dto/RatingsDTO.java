package com.spring.eventPlaning.dto;

import lombok.Data;

@Data
public class RatingsDTO {
    private Long rateId;
    private String nic;
    private String vendorNic;
    private Long ratings;
    private String reviews;
}

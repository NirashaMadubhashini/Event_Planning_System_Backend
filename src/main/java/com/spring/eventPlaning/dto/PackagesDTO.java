package com.spring.eventPlaning.dto;

import lombok.Data;

@Data
public class PackagesDTO {
    private Long packageId;
    private String packageName;
    private Long packagePrice;
    private String packageDuration;
    private String guestCount;
    private String listOfServices;
}

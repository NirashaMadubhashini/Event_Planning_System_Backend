package com.spring.eventPlaning.dto;

import lombok.Data;

@Data
public class VendorServiceInfoDTO {
    private String serviceName;
    private Long price;
    private String packageName;
    private String PackageNumber;
    private String Rating;

    // ignore this for now
}

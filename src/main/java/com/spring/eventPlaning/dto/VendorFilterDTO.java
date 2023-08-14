package com.spring.eventPlaning.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VendorFilterDTO extends PageableDTO {
    private String city;
    private String serviceType;
}

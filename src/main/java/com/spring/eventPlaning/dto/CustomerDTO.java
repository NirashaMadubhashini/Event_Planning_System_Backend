package com.spring.eventPlaning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String cusName;
    private String address;
    private String nic;
    private String email;
    private String contactNo;

}

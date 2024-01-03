package com.spring.eventPlaning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    private String username;
    private String password;
    private Set<String> roles;

}

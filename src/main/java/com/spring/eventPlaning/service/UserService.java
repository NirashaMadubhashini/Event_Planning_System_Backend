package com.spring.eventPlaning.service;

import com.spring.eventPlaning.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerUser(UserRegistrationDto registrationDto);
}

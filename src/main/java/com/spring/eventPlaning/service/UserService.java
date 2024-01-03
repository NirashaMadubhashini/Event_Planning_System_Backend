package com.spring.eventPlaning.service;

import com.spring.eventPlaning.dto.UserRegistrationDto;
import com.spring.eventPlaning.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerUser(UserRegistrationDto registrationDto);
}

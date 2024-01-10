package com.spring.eventPlaning.service;

import com.spring.eventPlaning.dto.UserRegistrationDto;
import com.spring.eventPlaning.dto.VendorDTO;
import com.spring.eventPlaning.entity.User;
import com.spring.eventPlaning.entity.Vendor;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerUser(UserRegistrationDto registrationDto);
    Vendor registerVendor(VendorDTO vendorDTO);

}

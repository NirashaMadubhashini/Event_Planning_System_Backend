package com.spring.eventPlaning.service.impl;

import com.spring.eventPlaning.dto.UserRegistrationDto;
import com.spring.eventPlaning.dto.VendorDTO;
import com.spring.eventPlaning.entity.Role;
import com.spring.eventPlaning.entity.User;
import com.spring.eventPlaning.entity.Vendor;
import com.spring.eventPlaning.enums.AvailabilityStatus;
import com.spring.eventPlaning.repo.RoleRepository;
import com.spring.eventPlaning.repo.UserRepo;
import com.spring.eventPlaning.repo.VendorRepo;
import com.spring.eventPlaning.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    private final VendorRepo vendorRepository;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepository roleRepository,VendorRepo vendorRepository) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository; // Set RoleRepository
        this.vendorRepository = vendorRepository;

    }


    @Override
    public User registerUser(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        try {
            Set<Role> roles = registrationDto.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new UsernameNotFoundException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());

            logger.debug("Roles found for user {}: {}", registrationDto.getUsername(), roles);
            user.setRoles(roles);
            User savedUser = userRepo.save(user);
            logger.debug("User saved with roles: {}", savedUser.getRoles());
            return savedUser;
        } catch (UsernameNotFoundException e) {
            logger.error("Registration failed for user {}: role not found", registrationDto.getUsername(), e);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during registration for user {}", registrationDto.getUsername(), e);
            throw e;
        }
    }

    public Vendor registerVendor(VendorDTO vendorDTO) {
        try {
            // User registration for Vendor
            UserRegistrationDto registrationDto = new UserRegistrationDto();
            registrationDto.setUsername(vendorDTO.getEmail()); // Email as username
            registrationDto.setPassword(passwordEncoder.encode(vendorDTO.getPassword())); // Encoding password
            registrationDto.setRoles(Set.of("ROLE_VENDOR")); // Setting role

            User user = registerUser(registrationDto); // Register user

            // Mapping VendorDTO to Vendor
            Vendor vendor = new Vendor();
            vendor.setVendorNic(vendorDTO.getVendorNic());
            vendor.setVendorName(vendorDTO.getVendorName());
            vendor.setAddress(vendorDTO.getAddress());
            vendor.setEmail(vendorDTO.getEmail());
            vendor.setContactNo(vendorDTO.getContactNo());
            vendor.setServiceName(vendorDTO.getServiceName());
            vendor.setServiceType(vendorDTO.getServiceType());
            vendor.setPrice(vendorDTO.getPrice());
            vendor.setPortfolio(vendorDTO.getPortfolio());
            vendor.setCity(vendorDTO.getCity());
            if (vendorDTO.getAvailability() != null) {
                vendor.setAvailability(AvailabilityStatus.getAvailabilityStatus(vendorDTO.getAvailability()));
            }
            vendor.setStartDate(vendorDTO.getStartDate());
            vendor.setEndDate(vendorDTO.getEndDate());

            // Save vendor details
            return vendorRepository.save(vendor);
        } catch (Exception e) {
            logger.error("Error occurred during vendor registration", e);
            throw new RuntimeException("Registration failed for vendor", e);
        }
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepo.findByUsername(username);
            if (user == null) {
                logger.error("User not found with username: {}", username);
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } catch (Exception e) {
            logger.error("Error occurred while loading user by username: {}", username, e);
            throw new UsernameNotFoundException("Error occurred while loading user by username: " + username, e);
        }
    }

}

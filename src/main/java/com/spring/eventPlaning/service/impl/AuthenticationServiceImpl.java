package com.spring.eventPlaning.service.impl;

import com.spring.eventPlaning.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     @Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            if (authentication.isAuthenticated()) {
                logger.info("Authentication successful for user: {}", username);
                return authentication;
            } else {
                logger.warn("Authentication failed for user: {}", username);
                throw new SecurityException("Authentication failed for user: " + username);
            }
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for user: {}", username, e);
            throw new SecurityException("Invalid credentials for user: " + username);
        } catch (Exception e) {
            logger.error("Authentication error for user: {}", username, e);
            throw new SecurityException("Authentication error for user: " + username, e);
        }
    }
}

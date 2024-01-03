package com.spring.eventPlaning.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication authenticate(String username, String password);

    // Add other method signatures related to authentication if necessary.
}

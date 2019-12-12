package com.netcracker.savenko.fapi.service;

import org.springframework.http.ResponseEntity;

public interface TokenService {
    ResponseEntity<?> authenticate(String username, String password);
}

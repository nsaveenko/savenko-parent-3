package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.User;
import org.springframework.http.ResponseEntity;

public interface TokenService {
    ResponseEntity<?> authenticate(String username, String password);
    User loadByToken(String token);
}

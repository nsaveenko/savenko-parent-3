package com.netcracker.savenko.fapi.service.impl;

import com.netcracker.savenko.fapi.models.User;
import com.netcracker.savenko.fapi.security.jwt.JwtTokenProvider;
import com.netcracker.savenko.fapi.service.TokenService;
import com.netcracker.savenko.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<?> authenticate(String username, String password) {
        Map<String, List<String>> errors = validateUsernameAndPassword(username, password);
        if (errors.size() != 0) {
            return ResponseEntity.badRequest().body(errors);
        } else {
            try {
                String username1 = username;
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                User user = userService.getByUsername(username);

                if (user == null) {
                    throw new UsernameNotFoundException("User with username: " + username + " not found");
                }
                String token = jwtTokenProvider.createToken(username, user.getRoleUserByIdRole());

                Map<Object, Object> response = new HashMap<>();
                response.put("username", username);
                response.put("user", user);
                response.put("token", token);

                return ResponseEntity.ok(response);
            } catch (AuthenticationException e) {
                throw new BadCredentialsException("Invalid username or password");
            }
        }
    }

    private Map<String, List<String>> validateUsernameAndPassword(String username, String password) {
        Map<String, List<String>> errors = new HashMap<String, List<String>>();
        List<String> usernameErrors = new ArrayList<>();
        List<String> passwordErrors = new ArrayList<>();
        if (username.length() > 25) {
            usernameErrors.add("Incorrect username length (length<26)");
        }

        if (password.length() > 25) {
            passwordErrors.add("Incorrect password length (length<26)");
        }

        if (password.contains(" ")) {
            passwordErrors.add("Password has whitespaces");
        }

        if (usernameErrors.size() != 0)
            errors.put("username", usernameErrors);
        if (passwordErrors.size() != 0)
            errors.put("password", passwordErrors);

        return errors;
    }
}

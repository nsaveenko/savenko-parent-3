package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.models.LogInParam;
import com.netcracker.savenko.fapi.models.User;
import com.netcracker.savenko.fapi.service.TokenService;
import com.netcracker.savenko.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAll());
    }

    @RequestMapping(value = "/followers/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getFollowers(@PathVariable int id){
        return ResponseEntity.ok(userService.getFollowers(id));
    }


    @RequestMapping(value = "/following/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getFollowing(@PathVariable int id){
        return ResponseEntity.ok(userService.getFollowing(id));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> getByUsernameAndPassword(@RequestBody LogInParam logInParam) {
        return tokenService.authenticate(logInParam.getUsername(), logInParam.getPassword());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/loadByToken")
    public User loadByToken(@RequestParam String token) {
        return tokenService.loadByToken(token);
    }
}
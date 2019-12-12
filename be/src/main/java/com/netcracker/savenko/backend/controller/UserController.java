package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserByUsernameAndPassword(@RequestParam String username,@RequestParam String password) {
        UserEntity user = userService.findByUsernameAndPassword(username, password);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/username1", method = RequestMethod.GET)
    public UserEntity getUserByUsername(@RequestParam String username) {
        UserEntity user =  userService.findByUsername(username);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserById(@PathVariable(name = "id") Integer id) {
        Optional<UserEntity> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<UserEntity> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }
}

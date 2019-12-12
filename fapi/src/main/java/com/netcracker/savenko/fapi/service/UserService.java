package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(Integer id);
    User getByUsernameAndPassword(String username, String password);
    User getByUsername(String username);
    User save(User user);
    void deleteUser(Integer id);
}

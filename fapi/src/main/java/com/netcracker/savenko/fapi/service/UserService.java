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
    List<User> getFollowers(int id);
    List<User> getFollowing(int id);
    List<User> findUserByUsername(String username, int id);
    boolean isExistByUsername(String username);
    boolean isExistByUsernameAndPassword(String username, String password);
}

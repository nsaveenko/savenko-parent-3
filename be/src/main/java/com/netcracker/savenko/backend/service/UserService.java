package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity save(UserEntity post);
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
    Optional<UserEntity> getUserById(Integer id);
    Iterable<UserEntity> getAllUser();
    void deleteUser(Integer id);
}

package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity save(UserEntity post);
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
    Optional<UserEntity> getUserById(Integer id);
    Iterable<UserEntity> getAllUser();
    List<UserEntity> getFollowingByIdFollowers(int userId);
    List<UserEntity> getFollowersByIdFollowing(int userId);
    void deleteUser(Integer id);
    List<UserEntity> findUserByUsername(String username, int id);
}

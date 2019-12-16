package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.repository.UserRepository;
import com.netcracker.savenko.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public Optional<UserEntity> getUserById(Integer userByIdUser) {
        return repository.findById(userByIdUser);
    }

    @Override
    public Iterable<UserEntity> getAllUser() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }
    @Override
    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<UserEntity> getFollowingByIdFollowers(int userId){
        return repository.getFollowingByIdFollowers(userId);
    }

    @Override
    public List<UserEntity> getFollowersByIdFollowing(int userId){
        return repository.getFollowersByIdFollowing(userId);
    }

    @Override
    public List<UserEntity> findUserByUsername(String username){
        return repository.findUserByUsername(username);
    }
}

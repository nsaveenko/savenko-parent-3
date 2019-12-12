package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.LikePostEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.repository.LikeRepository;
import com.netcracker.savenko.backend.repository.UserRepository;
import com.netcracker.savenko.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LikeServiceImpl implements LikeService {

    private LikeRepository repository;
    private UserRepository userRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository repository) {
        this.repository = repository;
    }

    @Override
    public LikePostEntity saveLike(LikePostEntity like) {
        return repository.save(like);
    }

    @Override
    public Optional<LikePostEntity> getLikeById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<LikePostEntity> getAllLike() {
        return repository.findAll();
    }

    @Override
    public void deleteLike(Integer id) {
        repository.deleteById(id);
    }
}

package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.PostEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.repository.PostRepository;
import com.netcracker.savenko.backend.repository.UserRepository;
import com.netcracker.savenko.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostServiceImpl implements PostService {

    private PostRepository repository;
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public PostEntity savePost(PostEntity post) {
        return repository.save(post);
    }

    @Override
    public Optional<PostEntity> getPostById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<PostEntity> getAllPost() {
        return repository.findAll();
    }

    @Override
    public void deletePost(Integer id) {
        repository.deleteById(id);
    }
}

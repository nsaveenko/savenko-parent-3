package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.CommentEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.repository.CommentRepository;
import com.netcracker.savenko.backend.repository.UserRepository;
import com.netcracker.savenko.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentServiceImpl implements CommentService {
    private CommentRepository repository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public CommentEntity saveComment(CommentEntity comment) {
        return repository.save(comment);
    }

    @Override
    public Optional<CommentEntity> getCommentById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<CommentEntity> getAllComment() {
        return repository.findAll();
    }

    @Override
    public void deleteComment(Integer id) {
        repository.deleteById(id);
    }
}

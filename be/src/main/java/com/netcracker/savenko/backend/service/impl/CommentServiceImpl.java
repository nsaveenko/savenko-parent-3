package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.CommentEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.repository.CommentRepository;
import com.netcracker.savenko.backend.repository.UserRepository;
import com.netcracker.savenko.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Page<CommentEntity> getAllCommentByPostId(int postId, Integer page, Integer size) {
        Pageable pageable = createPageable(page, size);
        return repository.findAllByPostId(postId, pageable);
    }

    @Override
    public void deleteComment(Integer id) {
        repository.deleteById(id);
    }

    private Pageable createPageable(Integer page, Integer size) {
        Pageable pageable;
        pageable = PageRequest.of(page, size);
        return pageable;
    }
}
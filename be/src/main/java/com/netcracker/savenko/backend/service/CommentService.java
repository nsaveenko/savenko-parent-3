package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.CommentEntity;
import com.netcracker.savenko.backend.entity.UserEntity;

import java.util.Optional;

public interface CommentService {
    CommentEntity saveComment(CommentEntity comment);
    Optional<CommentEntity> getCommentById(Integer id);
    Optional<UserEntity> getUserById(Integer userByIdUser);
    Iterable<CommentEntity> getAllComment();
    void deleteComment(Integer id);
}

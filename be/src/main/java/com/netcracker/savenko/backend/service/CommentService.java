package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.CommentEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentEntity saveComment(CommentEntity comment);
    Optional<CommentEntity> getCommentById(Integer id);
    Optional<UserEntity> getUserById(Integer userByIdUser);
    Page<CommentEntity> getAllCommentByPostId(int postId, Integer page, Integer size);
    void deleteComment(Integer id);
}

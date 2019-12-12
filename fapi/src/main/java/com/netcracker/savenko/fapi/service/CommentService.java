package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getCommentById(Integer id);
    Comment saveComment(Comment comment);
    void deleteComment(Integer idComment);
}

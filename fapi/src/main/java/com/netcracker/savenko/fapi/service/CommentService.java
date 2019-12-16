package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    //List<Comment> getAll();
    Page<Comment> getAllByPostId(int id, Integer page, Integer size);
    Comment getCommentById(Integer id);
    Comment saveComment(Comment comment);
    void deleteComment(Integer idComment);
}

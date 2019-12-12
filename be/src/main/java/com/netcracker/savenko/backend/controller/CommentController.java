package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.CommentEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommentEntity> getCommentById(@PathVariable(name = "id") Integer id) {
        Optional<CommentEntity> comment = commentService.getCommentById(id);
        if (comment.isPresent()) {
            return ResponseEntity.ok(comment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserById(@PathVariable(name = "userByIdUser") Integer id) {
        Optional<UserEntity> user = commentService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<CommentEntity> getAllComment() {
        return commentService.getAllComment();
    }

    @RequestMapping(method = RequestMethod.POST)
    public CommentEntity saveComment(@RequestBody CommentEntity comment) {
        return commentService.saveComment(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable(name = "id") Integer id) {
        commentService.deleteComment(id);
    }
}

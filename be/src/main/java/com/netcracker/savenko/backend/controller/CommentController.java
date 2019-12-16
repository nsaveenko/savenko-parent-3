package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.CommentEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<UserEntity> getUserById(@PathVariable(name = "id") Integer id) {
        Optional<UserEntity> user = commentService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/post/{id}/{page}/{size}", method = RequestMethod.GET)
    public Page<CommentEntity> getAllComment(@PathVariable(name = "id") int id, @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        return commentService.getAllCommentByPostId(id, page, size);
    }
//    @RequestMapping(value = "/post/", method = RequestMethod.GET)
//    public Page<CommentEntity> getAllCommentByPostId(@RequestParam int postId, @RequestParam int page, @RequestParam int size) {
//        return commentService.getAllCommentByPostId(postId, page, size);
//    }

    @RequestMapping(method = RequestMethod.POST)
    public CommentEntity saveComment(@RequestBody CommentEntity comment) {
        return commentService.saveComment(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable(name = "id") Integer id) {
        commentService.deleteComment(id);
    }
}
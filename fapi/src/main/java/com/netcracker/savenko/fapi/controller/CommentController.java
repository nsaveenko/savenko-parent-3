package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.models.Comment;
import com.netcracker.savenko.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping
    public ResponseEntity<List<Comment>> getAllComment() {
        return ResponseEntity.ok(commentService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment /*todo server validation*/) {
        if (comment != null) {
            return ResponseEntity.ok(commentService.saveComment(comment));
        }
        return null;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable int id) {
        commentService.deleteComment(Integer.valueOf(id));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }
}

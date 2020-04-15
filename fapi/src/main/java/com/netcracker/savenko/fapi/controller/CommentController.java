package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.controller.handler.HandlerService;
import com.netcracker.savenko.fapi.models.Comment;
import com.netcracker.savenko.fapi.models.CommentErrors;
import com.netcracker.savenko.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HandlerService handlerService;

//    @RequestMapping
//    public ResponseEntity<List<Comment>> getAllComment() {
//        return ResponseEntity.ok(commentService.getAll());
//    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Comment> getAllCommentByPostId(@RequestParam int postId, @RequestParam int page, @RequestParam int size) {
        return commentService.getAllByPostId(postId, page, size);
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<CommentErrors> saveComment(@RequestBody @Valid Comment comment /*todo server validation*/) {
//        return ResponseEntity.ok(new CommentErrors(commentService.saveComment(comment)));
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment /*todo server validation*/) {
        if (comment != null) {
            return ResponseEntity.ok(commentService.saveComment(comment));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommentErrors> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new CommentErrors(handlerService.handleMethodArgumentNotValid(ex)));
    }
}
package com.netcracker.savenko.fapi.models;

import java.util.HashMap;
import java.util.Map;

public class CommentErrors {
    private Comment comment;
    private Map<String, String> errors;

    public CommentErrors(Comment comment) {
        this.comment = comment;
        this.errors = new HashMap<>();
    }

    public CommentErrors(Map<String, String> errors){
        this.comment = new Comment();
        this.errors = errors;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

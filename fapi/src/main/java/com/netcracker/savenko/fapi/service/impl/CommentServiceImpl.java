package com.netcracker.savenko.fapi.service.impl;

import com.netcracker.savenko.fapi.models.Comment;
import com.netcracker.savenko.fapi.models.User;
import com.netcracker.savenko.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Comment> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Comment[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/comment/", Comment[].class);
        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
    }

    @Override
    public Comment getCommentById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/comment/" + id, Comment.class);
    }

    @Override
    public Comment saveComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        if (comment.getUserByIdUser() == null) {
            User user = restTemplate.getForObject(backendServerUrl + "/api/user/" + comment.getIdUser(), User.class);
            comment.setUserByIdUser(user);
        }
        return restTemplate.postForEntity(backendServerUrl + "/api/comment", comment, Comment.class).getBody();
    }

    @Override
    public void deleteComment(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/comment/" + id);
    }
}

package com.netcracker.savenko.fapi.service.impl;

import com.netcracker.savenko.fapi.models.Comment;
import com.netcracker.savenko.fapi.models.User;
import com.netcracker.savenko.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

//    @Override
//    public List<Comment> getAll() {
//        RestTemplate restTemplate = new RestTemplate();
//        Comment[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/comment/", Comment[].class);
//        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
//    }

    @Override
    public Page<Comment> getAllByPostId(int id, Integer page, Integer size) {
        RestTemplate restTemplate = new RestTemplate();
        Page<Comment> comments = restTemplate.getForObject(backendServerUrl + "/api/comment/post/"+ id +"/" + page +"/"+size, RestPageImpl.class);
        Pageable pageable = createPageable(page, size);
        return PageableExecutionUtils.getPage(comments.getContent(), pageable, comments::getTotalElements);
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

    private Pageable createPageable(Integer page, Integer size) {
        Pageable pageable;
        pageable = PageRequest.of(page, size);
        return pageable;
    }
}

package com.netcracker.savenko.fapi.service.impl;

import com.netcracker.savenko.fapi.models.Like;
import com.netcracker.savenko.fapi.models.User;
import com.netcracker.savenko.fapi.service.LikeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class LikeServiceImpl implements LikeService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Like> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Like[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/like/", Like[].class);
        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
    }

    @Override
    public Like getLikeById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/like/" + id, Like.class);
    }

    @Override
    public Like saveLike(Like like) {
        RestTemplate restTemplate = new RestTemplate();
        User user =  restTemplate.getForObject(backendServerUrl + "/api/user/" + like.getIdUser(), User.class);
        like.setUserByIdUser(user);
        return restTemplate.postForEntity(backendServerUrl + "/api/like", like, Like.class).getBody();
    }

    @Override
    public void deleteLike(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/like/" + id);
    }
}
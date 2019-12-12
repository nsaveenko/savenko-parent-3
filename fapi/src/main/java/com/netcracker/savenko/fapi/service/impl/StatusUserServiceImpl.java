package com.netcracker.savenko.fapi.service.impl;

import com.netcracker.savenko.fapi.models.StatusUser;
import com.netcracker.savenko.fapi.service.StatusUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class StatusUserServiceImpl implements StatusUserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<StatusUser> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        StatusUser[] statusUsersResponse = restTemplate.getForObject(backendServerUrl + "/api/statusUser", StatusUser[].class);
        return statusUsersResponse == null ? Collections.emptyList() : Arrays.asList(statusUsersResponse);
    }

    @Override
    public StatusUser getStatusUserById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/statusUser" + id, StatusUser.class);
    }
}
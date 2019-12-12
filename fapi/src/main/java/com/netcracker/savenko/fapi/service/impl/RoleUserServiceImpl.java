package com.netcracker.savenko.fapi.service.impl;

import com.netcracker.savenko.fapi.models.RoleUser;
import com.netcracker.savenko.fapi.service.RoleUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class RoleUserServiceImpl implements RoleUserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<RoleUser> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        RoleUser[] roleUsersResponse = restTemplate.getForObject(backendServerUrl + "/api/roleUser", RoleUser[].class);
        return roleUsersResponse == null ? Collections.emptyList() : Arrays.asList(roleUsersResponse);
    }

    @Override
    public RoleUser getRoleUserById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/roleUser" + id, RoleUser.class);
    }
}
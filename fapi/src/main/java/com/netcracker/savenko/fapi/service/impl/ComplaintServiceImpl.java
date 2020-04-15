package com.netcracker.savenko.fapi.service.impl;

import com.netcracker.savenko.fapi.models.Complaint;
import com.netcracker.savenko.fapi.service.ComplaintService;
import com.netcracker.savenko.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ComplaintServiceImpl implements ComplaintService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Complaint> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Complaint[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/complaint/", Complaint[].class);
        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
    }

    @Override
    public Complaint getComplaintById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/complaint/" + id, Complaint.class);
    }

//    @Override
//    public List<Complaint> getComplaintByStatusId(int id) {
//        RestTemplate restTemplate = new RestTemplate();
//        Complaint[] postResponse = restTemplate.getForObject(backendServerUrl + "api/complaint/status/" + id, Complaint[].class);
//        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
//    }
    @Override
    public List<Complaint> getComplaintOnPosts(int id){
        RestTemplate restTemplate = new RestTemplate();
        Complaint[] complaints = restTemplate.getForObject(backendServerUrl + "api/complaint/post/" + id, Complaint[].class);
        return complaints == null ? Collections.emptyList() : Arrays.asList(complaints);
    }

    @Override
    public Page<Complaint> getComplaintByStatusId(int id, Integer page, Integer size) {
        RestTemplate restTemplate = new RestTemplate();
        Page<Complaint> comments = restTemplate.getForObject(backendServerUrl + "api/complaint/status?id=" + id + "&page=" + page + "&size=" + size, RestPageImpl.class);
        Pageable pageable = createPageable(page, size);
        return PageableExecutionUtils.getPage(comments.getContent(), pageable, comments::getTotalElements);
    }

    @Override
    public String getStatusComplaint(int id){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/complaint/statusComplaint/" + id, String.class);
    }

    @Override
    public String getUsernameByComplaintId(int id){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/complaint/username/" + id, String.class);
    }

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "api/complaint/", complaint, Complaint.class).getBody();
    }

    @Override
    public Integer getUserId(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/complaint/user/post/" + id, Integer.class);
    }

    @Override
    public void deleteComplaint(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "api/complaint/" + id);
    }

    @Override
    public void deletePost(Integer idPost) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/post/" + idPost);
    }

    private Pageable createPageable(Integer page, Integer size) {
        Pageable pageable;
        pageable = PageRequest.of(page, size);
        return pageable;
    }
}

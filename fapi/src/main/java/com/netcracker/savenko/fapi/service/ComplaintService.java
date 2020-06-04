package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.Complaint;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComplaintService {
    Complaint getComplaintById(Integer id);
    Complaint saveComplaint(Complaint post);
    void deleteComplaint(Integer id);
    void deletePost(Integer id);
    List<Complaint> getAll();
    Page<Complaint> getComplaintByStatusId(int id, Integer page, Integer size);
    Integer getUserId(int id);
    String getStatusComplaint(int id);
    String getUsernameByComplaintId(int id);
    List<Complaint> getComplaintOnPosts(int id);
}
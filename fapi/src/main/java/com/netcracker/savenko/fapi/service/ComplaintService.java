package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint getComplaintById(Integer id);
    Complaint saveComplaint(Complaint post);
    void deleteComplaint(Integer id);
    void deletePost(Integer id);
    List<Complaint> getAll();
    List<Complaint> getComplaintByStatusId(int id);
}

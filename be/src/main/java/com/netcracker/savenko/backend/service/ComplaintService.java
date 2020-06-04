package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ComplaintService {
    ComplaintEntity saveComplaint(ComplaintEntity complaint);
    Optional<ComplaintEntity> getComplaintById(Integer id);
    void deleteComplaint(Integer id);
    void deletePost(Integer idPost);
    Iterable<ComplaintEntity> getAllComplaint();
    Page<ComplaintEntity> getComplaintByStatusId(int id, Integer page, Integer size);
    Integer getUserId(int id);
    String getStatusComplaintId(int id);
    String getUsernameByComplaintId(int id);
    List<ComplaintEntity> getComplaintOnPosts(int id);
}

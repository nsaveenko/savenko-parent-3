package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import com.netcracker.savenko.backend.entity.PostEntity;

import java.util.List;
import java.util.Optional;

public interface ComplaintService {
    ComplaintEntity saveComplaint(ComplaintEntity complaint);
    Optional<ComplaintEntity> getComplaintById(Integer id);
    void deleteComplaint(Integer id);
    void deletePost(Integer idPost);
    Iterable<ComplaintEntity> getAllComplaint();
    List<ComplaintEntity> getComplaintByStatusId(int id);
}

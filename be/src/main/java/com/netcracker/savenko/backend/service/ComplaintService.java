package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import com.netcracker.savenko.backend.entity.PostEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ComplaintService {
    ComplaintEntity saveComplaint(ComplaintEntity complaint);
    Optional<ComplaintEntity> getComplaintById(Integer id);
    void deleteComplaint(Integer id);
    void deletePost(Integer idPost);
    Iterable<ComplaintEntity> getAllComplaint();
    //List<ComplaintEntity> getComplaintByStatusId(int id);
    Page<ComplaintEntity> getComplaintByStatusId(int id, Integer page, Integer size);
    Integer getUserId(int id);
    String getStatusComplaintId(int id);
    String getUsernameByComplaintId(int id);
}

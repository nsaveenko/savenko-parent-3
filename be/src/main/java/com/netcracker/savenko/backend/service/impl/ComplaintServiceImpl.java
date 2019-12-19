package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import com.netcracker.savenko.backend.entity.PostEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.repository.ComplaintRepository;
import com.netcracker.savenko.backend.service.ComplaintService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ComplaintServiceImpl implements ComplaintService {

    private ComplaintRepository repository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository repository) {
        this.repository = repository;
    }

    @Override
    public ComplaintEntity saveComplaint(ComplaintEntity complaint) {
        return repository.save(complaint);
    }

    @Override
    public Optional<ComplaintEntity> getComplaintById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<ComplaintEntity> getComplaintByStatusId(int id) {
        return repository.getComplaintByStatusId(id);
    }

    @Override
    public Iterable<ComplaintEntity> getAllComplaint() {
        return repository.findAll();
    }

    @Override
    public void deleteComplaint(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deletePost(Integer idPost) {
        repository.deleteById(idPost);
    }

    @Override
    public Integer getUserId(int id) {
        return repository.getUserIdByPostIdAndComplaintId(id);
    }

    @Override
    public String getStatusComplaintId(int id) {
        return repository.getStatusComplaintByComplaintId(id);
    }

    @Override
    public String getUsernameByComplaintId(int id) {
        return repository.getUsernameByComplaintId(id);
    }

}

package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.StatusUserEntity;
import com.netcracker.savenko.backend.repository.StatusUserRepository;
import com.netcracker.savenko.backend.service.StatusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StatusUserServiceImpl implements StatusUserService {

    private StatusUserRepository repository;

    @Autowired
    public StatusUserServiceImpl(StatusUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<StatusUserEntity> getStatusUserById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<StatusUserEntity> getAllStatusUser() {
        return repository.findAll();
    }
}

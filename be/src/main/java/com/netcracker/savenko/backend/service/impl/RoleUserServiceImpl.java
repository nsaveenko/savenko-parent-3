package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.RoleUserEntity;
import com.netcracker.savenko.backend.repository.RoleUserRepository;
import com.netcracker.savenko.backend.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleUserServiceImpl implements RoleUserService {

    private RoleUserRepository repository;

    @Autowired
    public RoleUserServiceImpl(RoleUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<RoleUserEntity> getRoleUserById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<RoleUserEntity> getAllRoleUser() {
        return repository.findAll();
    }
}

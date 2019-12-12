package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.RoleUserEntity;

import java.util.Optional;

public interface RoleUserService {
    Optional<RoleUserEntity> getRoleUserById(Integer id);
    Iterable<RoleUserEntity> getAllRoleUser();
}

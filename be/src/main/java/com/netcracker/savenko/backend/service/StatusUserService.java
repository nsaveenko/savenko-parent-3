package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.StatusUserEntity;

import java.util.Optional;

public interface StatusUserService {
    Optional<StatusUserEntity> getStatusUserById(Integer id);
    Iterable<StatusUserEntity> getAllStatusUser();
}

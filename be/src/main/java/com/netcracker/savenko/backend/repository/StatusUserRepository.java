package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.StatusUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusUserRepository extends CrudRepository<StatusUserEntity, Integer>{
}

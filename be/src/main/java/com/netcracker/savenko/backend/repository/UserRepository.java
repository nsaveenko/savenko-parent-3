package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
}
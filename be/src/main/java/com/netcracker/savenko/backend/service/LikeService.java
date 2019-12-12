package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.LikePostEntity;
import com.netcracker.savenko.backend.entity.UserEntity;

import java.util.Optional;

public interface LikeService {
    LikePostEntity saveLike(LikePostEntity like);
    Optional<LikePostEntity> getLikeById(Integer id);
    Optional<UserEntity> getUserById(Integer userByIdUser);
    Iterable<LikePostEntity> getAllLike();
    void deleteLike(Integer id);
}

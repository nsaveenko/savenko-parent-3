package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.PostEntity;
import com.netcracker.savenko.backend.entity.UserEntity;

import java.util.Optional;

public interface PostService {
    PostEntity savePost(PostEntity post);
    Optional<PostEntity> getPostById(Integer id);
    Optional<UserEntity> getUserById(Integer userByIdUser);
    Iterable<PostEntity> getAllPost();
    void deletePost(Integer id);
}

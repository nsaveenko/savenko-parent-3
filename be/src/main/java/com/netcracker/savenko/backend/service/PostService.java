package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.PostEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostEntity savePost(PostEntity post);
    Optional<PostEntity> getPostById(Integer id);
    Optional<UserEntity> getUserById(Integer userByIdUser);
    Iterable<PostEntity> getAllPost();
    List<PostEntity> getPostBySub(int userId);
    List<PostEntity> getPostByCurrUser(int userId);
    void deletePost(Integer id);
    Integer countPostByUserId(int userId);
    PostEntity getPostByPostIdAndComplaintId(int postId, int complaintId);
}

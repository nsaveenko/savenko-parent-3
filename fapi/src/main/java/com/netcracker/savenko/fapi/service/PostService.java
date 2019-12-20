package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    List<Post> getPostBySub(int userId);
    List<Post> getPostByCurrUser(int userId);
//    Page<Post> getPostBySub(int id, Integer page, Integer size);
//    Page<Post> getPostByCurrUser(int id, Integer page, Integer size);
    Post getPostById(Integer id);
    Post savePost(Post post);
    void deletePost(Integer idPost);
    Integer getCountPostByUserId(Integer id);
}
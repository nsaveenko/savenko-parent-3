package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.Post;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post getPostById(Integer id);
    Post savePost(Post post);
    void deletePost(Integer idPost);
}
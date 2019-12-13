package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    //PostEntity[] FindPostByUserId(Integer userByIdUser);
}

package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Integer> {
    //PostEntity[] FindPostByUserId(Integer userByIdUser, Pageable pageable);
    @Query(value = "select post.* from post " +
            "join user on post.id_user = user.id " +
            "join subscriptions on subscriptions.id_following = user.id " +
            "where subscriptions.id_followers = (?1) " +
            "order by post.date_post desc, post.id desc", nativeQuery = true)
    List<PostEntity> findPostBySub(int userId);

    @Query(value = "select post. * from post " +
            "join user on post.id_user = user.id " +
            "where user.id = (?1) " +
            "order by post.date_post desc, post.id desc", nativeQuery = true)
    List<PostEntity> findPostByCurrUser(int userId);

    @Query(value = "select count(post.id) from post " +
            "join user on post.id_user = user.id " +
            "where user.id=(?1)", nativeQuery = true)
    Integer countPostByUserId(int userId);
}

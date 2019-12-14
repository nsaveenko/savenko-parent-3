package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
    @Query(value = "select user.* from subscriptions join user on subscriptions.id_following = user.id where id_followers=(?1)", nativeQuery = true)
    List<UserEntity> getFollowingByIdFollowers(int userId);
    @Query(value = "select user.* from subscriptions join user on subscriptions.id_followers = user.id where id_following=(?1)", nativeQuery = true)
    List<UserEntity> getFollowersByIdFollowing(int userId);
}
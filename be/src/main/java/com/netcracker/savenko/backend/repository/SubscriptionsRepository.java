package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.SubscriptionsEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsRepository extends CrudRepository<SubscriptionsEntity, Integer> {
//    @Query(value = "select * from subscriptions join user on subscriptions.id_following = user.id where id_followers=?1", nativeQuery = true)
//    SubscriptionsEntity[] getFollowersByIdFollowing(SubscriptionsEntity userByIdFollowing);
//    @Query(value = "select * from subscriptions join user on subscriptions.id_followers = user.id where id_following=?1", nativeQuery = true)
//    SubscriptionsEntity[] getFollowersByIdFollowers(SubscriptionsEntity userByIdFollowers);
}

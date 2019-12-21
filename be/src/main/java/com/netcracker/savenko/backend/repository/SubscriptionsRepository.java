package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.SubscriptionsEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsRepository extends CrudRepository<SubscriptionsEntity, Integer> {

    @Query(value = "select subscriptions.id from subscriptions" +
            " join user on subscriptions.id_following = user.id " +
            "where id_followers=:currUserId and id_following=:follId ", nativeQuery = true)
    Integer getSubId(@Param("currUserId") int currUserId, @Param("follId") int follId);
}

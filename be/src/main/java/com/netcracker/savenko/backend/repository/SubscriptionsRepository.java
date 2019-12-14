package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.SubscriptionsEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsRepository extends CrudRepository<SubscriptionsEntity, Integer> {

}

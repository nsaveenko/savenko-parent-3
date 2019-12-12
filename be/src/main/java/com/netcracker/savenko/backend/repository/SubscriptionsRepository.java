package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.SubscriptionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsRepository extends CrudRepository<SubscriptionsEntity, Integer> {
}

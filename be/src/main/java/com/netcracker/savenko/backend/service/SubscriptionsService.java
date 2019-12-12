package com.netcracker.savenko.backend.service;

import com.netcracker.savenko.backend.entity.SubscriptionsEntity;

import java.util.Optional;

public interface SubscriptionsService {
    SubscriptionsEntity saveSubscription(SubscriptionsEntity subscriptions);
    Optional<SubscriptionsEntity> getSubscriptionById(Integer id);
    Iterable<SubscriptionsEntity> getAllSubscriptions();
    void deleteSubscription(Integer id);
}

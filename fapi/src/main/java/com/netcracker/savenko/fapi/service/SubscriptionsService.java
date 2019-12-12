package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.Subscriptions;

import java.util.List;

public interface SubscriptionsService {
    List<Subscriptions> getAllSubscriptions();
    Subscriptions getSubscriptionById(Integer id);
    Subscriptions saveSubscription(Subscriptions subscription);
    void deleteSubscription(Integer id);
}

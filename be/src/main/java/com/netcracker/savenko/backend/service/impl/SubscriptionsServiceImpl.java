package com.netcracker.savenko.backend.service.impl;

import com.netcracker.savenko.backend.entity.SubscriptionsEntity;
import com.netcracker.savenko.backend.repository.SubscriptionsRepository;
import com.netcracker.savenko.backend.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubscriptionsServiceImpl implements SubscriptionsService {

    private SubscriptionsRepository repository;

    @Autowired
    public SubscriptionsServiceImpl(SubscriptionsRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubscriptionsEntity saveSubscription(SubscriptionsEntity subscriptions) {
        return repository.save(subscriptions);
    }

    @Override
    public Optional<SubscriptionsEntity> getSubscriptionById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<SubscriptionsEntity> getAllSubscriptions() {
        return repository.findAll();
    }

    @Override
    public void deleteSubscription(Integer id) {
        repository.deleteById(id);
    }
}

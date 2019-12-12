package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.SubscriptionsEntity;
import com.netcracker.savenko.backend.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {

    private SubscriptionsService subscriptionsService;

    @Autowired
    public SubscriptionsController(SubscriptionsService subscriptionsService) {
        this.subscriptionsService = subscriptionsService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SubscriptionsEntity> getSubscriptionsById(@PathVariable(name = "id") Integer id) {
        Optional<SubscriptionsEntity> subscriptions = subscriptionsService.getSubscriptionById(id);
        if (subscriptions.isPresent()) {
            return ResponseEntity.ok(subscriptions.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<SubscriptionsEntity> getAllSubscriptions() {
        return subscriptionsService.getAllSubscriptions();
    }

    @RequestMapping(method = RequestMethod.POST)
    public SubscriptionsEntity saveSubscription(@RequestBody SubscriptionsEntity subscription) {
        return subscriptionsService.saveSubscription(subscription);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSubscription(@PathVariable(name = "id") Integer id) {
        subscriptionsService.deleteSubscription(id);
    }
}

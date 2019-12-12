package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.models.Subscriptions;
import com.netcracker.savenko.fapi.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {

    @Autowired
    private SubscriptionsService subscriptionsService;

    @RequestMapping
    public ResponseEntity<List<Subscriptions>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionsService.getAllSubscriptions());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSubscription(@PathVariable String id) {
        subscriptionsService.deleteSubscription(Integer.valueOf(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Subscriptions> getSubscriptionById(@PathVariable String id) throws InterruptedException {
        int userId = Integer.parseInt(id);
        return ResponseEntity.ok(subscriptionsService.getSubscriptionById(userId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Subscriptions> saveSubscription(@RequestBody Subscriptions subscription /*todo server validation*/) {
        if (subscription != null) {
            return ResponseEntity.ok(subscriptionsService.saveSubscription(subscription));
        }
        return null;
    }
}

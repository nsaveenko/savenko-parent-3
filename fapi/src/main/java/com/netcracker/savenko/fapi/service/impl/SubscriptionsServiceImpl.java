package com.netcracker.savenko.fapi.service.impl;

import com.netcracker.savenko.fapi.models.Subscriptions;
import com.netcracker.savenko.fapi.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class SubscriptionsServiceImpl implements SubscriptionsService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Subscriptions> getAllSubscriptions() {
        RestTemplate restTemplate = new RestTemplate();
        Subscriptions[] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions", Subscriptions[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public Subscriptions getSubscriptionById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/subscriptions/" + id, Subscriptions.class);
    }

    @Override
    public Subscriptions saveSubscription(Subscriptions subscription) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions", subscription, Subscriptions.class).getBody();
    }

    @Override
    public void deleteSubscription(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "api/subscriptions/" + id);
    }

    @Override
    public Integer getSubId(int currUserId, int follId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/subscriptions/" + currUserId + "/" + follId, Integer.class);
    }
}

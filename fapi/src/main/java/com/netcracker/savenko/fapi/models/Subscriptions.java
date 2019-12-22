package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscriptions {

    @NotEmpty(message = "id is required")
    private int id;

    @NotEmpty(message = "userByIdFollowers is required")
    private User userByIdFollowers;

    @NotEmpty(message = "userByIdFollowing is required")
    private User userByIdFollowing;

    public Subscriptions() {
    }

    public Subscriptions(int id, User userByIdFollowers, User userByIdFollowing) {
        this.id = id;
        this.userByIdFollowers = userByIdFollowers;
        this.userByIdFollowing = userByIdFollowing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserByIdFollowers() {
        return userByIdFollowers;
    }

    public void setUserByIdFollowers(User userByIdFollowers) {
        this.userByIdFollowers = userByIdFollowers;
    }

    public User getUserByIdFollowing() {
        return userByIdFollowing;
    }

    public void setUserByIdFollowing(User userByIdFollowing) {
        this.userByIdFollowing = userByIdFollowing;
    }
}

package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusUser {
    private int id;
    private String statusUser;

    public StatusUser(){}

    public StatusUser(int id, String statusUser){
        this.id = id;
        this.statusUser = statusUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String status) {
        this.statusUser = statusUser;
    }
}

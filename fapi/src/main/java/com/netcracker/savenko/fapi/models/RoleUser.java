package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleUser {
    private int id;
    private String roleUser;

    public RoleUser(){}

    public RoleUser(int id, String roleUser){
        this.id = id;
        this.roleUser = roleUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String role) {
        this.roleUser = roleUser;
    }
}

package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int id;
    private String username;
    private String flName;
    private String password;
    private RoleUser roleUserByIdRole;
    private StatusUser statusUserByIdStatus;

    public User(){}

    public User(int id, String username, String flName, String password, RoleUser roleUserByIdRole, StatusUser statusUserByIdStatus){
        this.id = id;
        this.username = username;
        this.flName = flName;
        this.password = password;
        this.roleUserByIdRole = roleUserByIdRole;
        this.statusUserByIdStatus = statusUserByIdStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() { return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFlName() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName = flName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleUser getRoleUserByIdRole() {
        return roleUserByIdRole;
    }

    public void setRoleUserByIdRole(RoleUser roleUserByIdRole) {
        this.roleUserByIdRole = roleUserByIdRole;
    }

    public StatusUser getStatusUserByIdStatus() {
        return statusUserByIdStatus;
    }

    public void setStatusUserByIdStatus(StatusUser statusUserByIdStatus) {
        this.statusUserByIdStatus = statusUserByIdStatus;
    }
}

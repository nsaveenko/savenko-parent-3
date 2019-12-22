package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Like {
    @NotEmpty
    private int id;
    @NotEmpty
    private int idPost;
    @NotEmpty
    private int idUser;
    @NotEmpty
    private User userByIdUser;

    public Like() {}

    public Like(int id, int idPost, int idUser, User userByIdUser){
        this.id = id;
        this.idPost = idPost;
        this.idUser = idUser;
        this.userByIdUser = userByIdUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}

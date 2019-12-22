package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@JsonIgnoreProperties(ignoreUnknown = true)

public class Comment {

    @NotEmpty
    private int id;

    @NotEmpty
    private int idPost;

    @Min(value = 3, message = "Incorrect comment")
    @Max(value = 50, message = "Incorrect comment")
    private String tex;

    @NotEmpty
    private String dataPost;

    private int idUser;

    @NotEmpty
    private User userByIdUser;

    public Comment(){}

    public Comment(int id, int idPost, String tex, String dataPost, int idUser, User userByIdUser){
        this.id = id;
        this.idPost = idPost;
        this.tex = tex;
        this.dataPost = dataPost;
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

    public String getTex() {
        return tex;
    }

    public void setTex(String tex) {
        this.tex = tex;
    }

    public String getDataPost() {
        return dataPost;
    }

    public void setDataPost(String dataPost) {
        this.dataPost = dataPost;
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

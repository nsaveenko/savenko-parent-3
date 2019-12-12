package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.jws.soap.SOAPBinding;
import java.io.File;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private int id;
    private int idUser;
    private String filePath;
    private String datePost;
    private String text;
    private File file;
    private User userByIdUser;

    public Post() {}

    public Post(int id, int idUser, String filePath, String datePost, String text, File file, User userByIdUser) {
        this.id = id;
        this.idUser = idUser;
        this.filePath = filePath;
        this.datePost = datePost;
        this.text = text;
        this.file = file;
        this.userByIdUser = userByIdUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}

package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.File;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    @NotEmpty(message = "id is required")
    private int id;

    @NotEmpty(message = "idUser is required")
    private int idUser;

    @NotEmpty(message = "filePath is required")
    private String filePath;

    @NotEmpty(message = "datePost is required")
    private String datePost;

    @NotEmpty(message = "text is required")
    @Min(1)
    @Max(200)
    private String text;

    @NotEmpty(message = "file is required")
    private File file;

    @NotEmpty(message = "userByIdUser is required")
    private User userByIdUser;

    public Post() {
    }

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

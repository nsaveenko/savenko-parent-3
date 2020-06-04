package com.netcracker.savenko.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Complaint {
    @NotEmpty
    private int id;
    @NotEmpty
    private int idUser;
    @NotEmpty
    private String dateComplaint;
    @NotEmpty
    @Min(value = 3, message = "Incorrect complaint")
    private String complaint;
    @NotEmpty
    private int postId;
    @NotEmpty
    private int idStatusComplaint;

    public Complaint(){}

    public Complaint(int id, int idUser, String dateComplaint, String complaint, int postId, int idStatusComplaint) {
        this.id = id;
        this.idUser = idUser;
        this.dateComplaint = dateComplaint;
        this.complaint = complaint;
        this.postId = postId;
        this.idStatusComplaint = idStatusComplaint;
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

    public void setDateComplaint(String dateComplaint) {
        this.dateComplaint = dateComplaint;
    }

    public String getDateComplaint() {
        return dateComplaint;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public int getPostId() {
        return postId;
    }

    public void setPost(int postId) {
        this.postId = postId;
    }

    public int getIdStatusComplaint() {
        return idStatusComplaint;
    }

    public void setIdStatusComplaint(int idStatusComplaint) {
        this.idStatusComplaint = idStatusComplaint;
    }
}

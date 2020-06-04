package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "complaint", schema = "backend", catalog = "")
public class ComplaintEntity {
    private int id;
    private Integer idUser;
    private Date dateComplaint;
    private String complaint;
    private int postId;
    private Integer idStatusComplaint;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_user")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "date_complaimnt")
    public Date getDateComplaint() {
        return dateComplaint;
    }

    public void setDateComplaint(Date dateComplaint) {
        this.dateComplaint = dateComplaint;
    }

    @Basic
    @Column(name = "complaint")
    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    @Basic
    @Column(name = "id_post")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "id_status_complaint")
    public Integer getIdStatusComplaint() {
        return idStatusComplaint;
    }

    public void setIdStatusComplaint(Integer idStatusComplaint) {
        this.idStatusComplaint = idStatusComplaint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplaintEntity that = (ComplaintEntity) o;
        return id == that.id &&
                Objects.equals(idUser, that.idUser) &&
                Objects.equals(dateComplaint, that.dateComplaint) &&
                Objects.equals(complaint, that.complaint) &&
                Objects.equals(postId, that.postId) &&
                Objects.equals(idStatusComplaint, that.idStatusComplaint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, dateComplaint, postId, complaint, idStatusComplaint);
    }
}

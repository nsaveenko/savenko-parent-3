package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status_complaint", schema = "backend", catalog = "")
public class StatusComplaintEntity {
    private int id;
    private String statusComplaint;

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
    @Column(name = "status_complaint")
    public String getStatusComplaint() {
        return statusComplaint;
    }

    public void setStatusComplaint(String statusComplaint) {
        this.statusComplaint = statusComplaint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusComplaintEntity that = (StatusComplaintEntity) o;
        return id == that.id &&
                Objects.equals(statusComplaint, that.statusComplaint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusComplaint);
    }
}

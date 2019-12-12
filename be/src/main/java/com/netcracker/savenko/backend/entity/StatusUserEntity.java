package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status_user", schema = "backend", catalog = "")
public class StatusUserEntity {
    private int id;
    private String statusUser;

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
    @Column(name = "status_user")
    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusUserEntity that = (StatusUserEntity) o;
        return id == that.id &&
                Objects.equals(statusUser, that.statusUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusUser);
    }
}

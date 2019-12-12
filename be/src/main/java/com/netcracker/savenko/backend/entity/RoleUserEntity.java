package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role_user", schema = "backend", catalog = "")
public class RoleUserEntity {
    private int id;
    private String roleUser;

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
    @Column(name = "role_user")
    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleUserEntity that = (RoleUserEntity) o;
        return id == that.id &&
                Objects.equals(roleUser, that.roleUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleUser);
    }
}

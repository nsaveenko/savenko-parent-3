package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "backend", catalog = "")
public class UserEntity {
    private int id;
    private String username;
    private String flName;
    private String password;
    private RoleUserEntity roleUserByIdRole;
    private StatusUserEntity statusUserByIdStatus;

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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "fl_name")
    public String getFlName() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName = flName;
    }

    @Basic
    @Column(name = "password_user")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // проверяет не одна и та же это ссылка
        if (o == null || getClass() != o.getClass()) return false; //если null - false, this null быть не может  || при вызове можно передать что угодно тк приходит тип object(совпадение типов)
        UserEntity that = (UserEntity) o; //расширяет область видимости ссылки
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(flName, that.flName) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, flName, password);
    }

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    public RoleUserEntity getRoleUserByIdRole() {
        return roleUserByIdRole;
    }

    public void setRoleUserByIdRole(RoleUserEntity roleUserByIdRole) {
        this.roleUserByIdRole = roleUserByIdRole;
    }

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    public StatusUserEntity getStatusUserByIdStatus() {
        return statusUserByIdStatus;
    }

    public void setStatusUserByIdStatus(StatusUserEntity statusUserByIdStatus) {
        this.statusUserByIdStatus = statusUserByIdStatus;
    }
}

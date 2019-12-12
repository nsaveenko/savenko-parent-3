package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "like_post", schema = "backend", catalog = "")
public class LikePostEntity {
    private int id;
    private Integer idPost;
    private UserEntity userByIdUser;

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
    @Column(name = "id_post")
    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikePostEntity that = (LikePostEntity) o;
        return id == that.id &&
                Objects.equals(idPost, that.idPost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPost);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    public UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}

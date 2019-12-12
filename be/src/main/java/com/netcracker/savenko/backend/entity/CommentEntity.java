package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "comment_post", schema = "backend", catalog = "")
public class CommentEntity {
    private int id;
    private String tex;
    private Date dataPost;
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
    @Column(name = "tex")
    public String getTex() {
        return tex;
    }

    public void setTex(String tex) {
        this.tex = tex;
    }

    @Basic
    @Column(name = "data_post")
    public Date getDataPost() {
        return dataPost;
    }

    public void setDataPost(Date dataPost) {
        this.dataPost = dataPost;
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
        CommentEntity that = (CommentEntity) o;
        return id == that.id &&
                Objects.equals(tex, that.tex) &&
                Objects.equals(dataPost, that.dataPost) &&
                Objects.equals(idPost, that.idPost) &&
                Objects.equals(userByIdUser, that.userByIdUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tex, dataPost, idPost);
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

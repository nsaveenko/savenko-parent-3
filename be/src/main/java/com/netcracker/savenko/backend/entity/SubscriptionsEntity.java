package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subscriptions", schema = "backend", catalog = "")
public class SubscriptionsEntity {
    private UserEntity userByIdFollowers;
    private UserEntity userByIdFollowing;
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_followers", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByIdFollowers() {
        return userByIdFollowers;
    }

    public void setUserByIdFollowers(UserEntity userByIdFollowers) {
        this.userByIdFollowers = userByIdFollowers;
    }

    @ManyToOne
    @JoinColumn(name = "id_following", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByIdFollowing() {
        return userByIdFollowing;
    }

    public void setUserByIdFollowing(UserEntity userByIdFollowing) {
        this.userByIdFollowing = userByIdFollowing;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionsEntity that = (SubscriptionsEntity) o;
        return id == that.id &&
                Objects.equals(userByIdFollowers, that.userByIdFollowers) &&
                Objects.equals(userByIdFollowing, that.userByIdFollowing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userByIdFollowers, userByIdFollowing);
    }
}

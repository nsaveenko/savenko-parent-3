package com.netcracker.savenko.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hashtag", schema = "backend", catalog = "")
public class HashtagEntity {
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashtagEntity that = (HashtagEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

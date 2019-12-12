package com.netcracker.savenko.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "post_hashtag", schema = "backend", catalog = "")
public class PostHashtagEntity {
    private HashtagEntity hashtagByName;
    private PostEntity postByIdPost;
    private String id;

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name")
    public HashtagEntity getHashtagByName() {
        return hashtagByName;
    }

    public void setHashtagByName(HashtagEntity hashtagByName) {
        this.hashtagByName = hashtagByName;
    }

    @ManyToOne
    @JoinColumn(name = "id_post", referencedColumnName = "id")
    public PostEntity getPostByIdPost() {
        return postByIdPost;
    }

    public void setPostByIdPost(PostEntity postByIdPost) {
        this.postByIdPost = postByIdPost;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }
}

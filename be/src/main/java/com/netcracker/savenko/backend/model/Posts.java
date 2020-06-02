package com.netcracker.savenko.backend.model;

public class Posts {
    private int postId;
    private int count;

    public Posts(int postId, int count){
        this.postId = postId;
        this.count = count;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
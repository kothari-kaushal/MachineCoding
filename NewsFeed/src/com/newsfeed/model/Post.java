package com.newsfeed.model;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private String id;
    private String userId;
    private String content;
    private int numOfUpvote;
    private int numOfDownvote;
    private long createdAt;
    private long updatedAt;
    private List<String> replies;

    private Post() {

    }

    public Post(String id, String userId, String content) {

        this.id = id;
        this.userId = userId;
        this.content = content;
        this.replies = new ArrayList<>();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public int getNumOfUpvote() {

        return numOfUpvote;
    }

    public void setNumOfUpvote(int numOfUpvote) {

        this.numOfUpvote = numOfUpvote;
    }

    public int getNumOfDownvote() {

        return numOfDownvote;
    }

    public void setNumOfDownvote(int numOfDownvote) {

        this.numOfDownvote = numOfDownvote;
    }

    public long getUpdatedAt() {

        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {

        this.updatedAt = updatedAt;
    }

    public String getId() {

        return id;
    }

    public String getUserId() {

        return userId;
    }

    public long getCreatedAt() {

        return createdAt;
    }

    public List<String> getReplies() {

        return replies;
    }

    public void setReplies(List<String> replies) {

        this.replies = replies;
    }
}

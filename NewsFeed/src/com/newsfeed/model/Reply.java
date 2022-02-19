package com.newsfeed.model;

public class Reply {

    private String id;
    private String userId;
    private String postId;
    private String content;
    private int numOfUpvote;
    private int numOfDownvote;
    private long createdAt;
    private long updatedAt;

    public Reply(String id, String userId, String postId, String content) {

        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
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

    public String getPostId() {

        return postId;
    }

    public long getCreatedAt() {

        return createdAt;
    }
}

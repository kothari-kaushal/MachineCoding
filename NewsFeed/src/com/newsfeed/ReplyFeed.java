package com.newsfeed;

public class ReplyFeed {

    private String commentId;
    private String commentBy;
    private String commentContent;
    private String commentedTimeStamp;

    public String getCommentId() {

        return commentId;
    }

    public void setCommentId(String commentId) {

        this.commentId = commentId;
    }

    public String getCommentBy() {

        return commentBy;
    }

    public void setCommentBy(String commentBy) {

        this.commentBy = commentBy;
    }

    public String getCommentContent() {

        return commentContent;
    }

    public void setCommentContent(String commentContent) {

        this.commentContent = commentContent;
    }

    public String getCommentedTimeStamp() {

        return commentedTimeStamp;
    }

    public void setCommentedTimeStamp(String commentedTimeStamp) {

        this.commentedTimeStamp = commentedTimeStamp;
    }
}

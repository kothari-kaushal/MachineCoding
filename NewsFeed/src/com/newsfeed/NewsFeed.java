package com.newsfeed;

import java.util.List;

public class NewsFeed {

    private String postId;
    private int numOfUpVote;
    private int numOfDownVote;
    private String postedBy;
    private String postContent;
    private String postedTimeStamp;
    private List<ReplyFeed> replyFeeds;

    public String getPostId() {

        return postId;
    }

    public void setPostId(String postId) {

        this.postId = postId;
    }

    public int getNumOfUpVote() {

        return numOfUpVote;
    }

    public void setNumOfUpVote(int numOfUpVote) {

        this.numOfUpVote = numOfUpVote;
    }

    public int getNumOfDownVote() {

        return numOfDownVote;
    }

    public void setNumOfDownVote(int numOfDownVote) {

        this.numOfDownVote = numOfDownVote;
    }

    public String getPostedBy() {

        return postedBy;
    }

    public void setPostedBy(String postedBy) {

        this.postedBy = postedBy;
    }

    public String getPostContent() {

        return postContent;
    }

    public void setPostContent(String postContent) {

        this.postContent = postContent;
    }

    public String getPostedTimeStamp() {

        return postedTimeStamp;
    }

    public void setPostedTimeStamp(String postedTimeStamp) {

        this.postedTimeStamp = postedTimeStamp;
    }

    public List<ReplyFeed> getReplyFeeds() {

        return replyFeeds;
    }

    public void setReplyFeeds(List<ReplyFeed> replyFeeds) {

        this.replyFeeds = replyFeeds;
    }
}

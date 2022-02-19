package com.newsfeed.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;
    private List<String> followers;
    private List<String> following;
    private boolean isLoggedIn;

    private User() {

    }

    public User(String id, String name) {

        this.id = id;
        this.name = name;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.isLoggedIn = false;
    }

    public List<String> getFollowers() {

        return followers;
    }

    public void setFollowers(List<String> followers) {

        this.followers = followers;
    }

    public List<String> getFollowing() {

        return following;
    }

    public void setFollowing(List<String> following) {

        this.following = following;
    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public boolean isLoggedIn() {

        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {

        isLoggedIn = loggedIn;
    }
}

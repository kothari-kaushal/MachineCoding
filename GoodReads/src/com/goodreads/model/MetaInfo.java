package com.goodreads.model;

public class MetaInfo {

    private String started;
    private float rating;
    private int currentPage;

    public String getStarted() {

        return started;
    }

    public void setStarted(String started) {

        this.started = started;
    }

    public float getRating() {

        return rating;
    }

    public void setRating(float rating) {

        this.rating = rating;
    }

    public int getCurrentPage() {

        return currentPage;
    }

    public void setCurrentPage(int currentPage) {

        this.currentPage = currentPage;
    }
}

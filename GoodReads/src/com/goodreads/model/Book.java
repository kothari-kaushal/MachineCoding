package com.goodreads.model;

import java.util.List;

public class Book {

    private String id;
    private String name;
    private List<String> content;
    private Float rating;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<String> getContent() {

        return content;
    }

    public void setContent(List<String> content) {

        this.content = content;
    }

    public Float getRating() {

        return rating;
    }

    public void setRating(Float rating) {

        this.rating = rating;
    }
}

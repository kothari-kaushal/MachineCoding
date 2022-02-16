package com.goodreads.model;

import java.util.List;
import java.util.Map;

public class User {

    private String id;
    private String name;
    private List<String> books;
    private Map<String, MetaInfo> bookIdToMetaInfo;
    private String currentBook;
    private boolean loggedIn;

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

    public List<String> getBooks() {

        return books;
    }

    public void setBooks(List<String> books) {

        this.books = books;
    }

    public Map<String, MetaInfo> getBookIdToMetaInfo() {

        return bookIdToMetaInfo;
    }

    public void setBookIdToMetaInfo(Map<String, MetaInfo> bookIdToMetaInfo) {

        this.bookIdToMetaInfo = bookIdToMetaInfo;
    }

    public String getCurrentBook() {

        return currentBook;
    }

    public void setCurrentBook(String currentBook) {

        this.currentBook = currentBook;
    }

    public boolean isLoggedIn() {

        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {

        this.loggedIn = loggedIn;
    }
}

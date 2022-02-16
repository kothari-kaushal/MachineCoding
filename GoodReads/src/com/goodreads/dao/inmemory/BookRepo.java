package com.goodreads.dao.inmemory;

import com.goodreads.dao.Repo;
import com.goodreads.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepo implements Repo<Book> {

    private List<Book> allBooks;

    public BookRepo() {

        this.allBooks = new ArrayList<>();
    }

    @Override
    public Book get(String id) {

        return this.allBooks.stream().filter(book -> book.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalStateException("book not found"));
    }

    @Override
    public List<Book> getAll() {

        return this.allBooks;
    }

    @Override
    public Book create(Book obj) {

        this.allBooks.add(obj);
        return obj;
    }

    @Override
    public Book update(Book obj) {

        return obj;
    }
}

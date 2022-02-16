package com.goodreads.services;

import com.goodreads.dao.Repo;
import com.goodreads.model.Book;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BookService {

    private final Repo<Book> booksRepo;

    public BookService(Repo<Book> booksRepo) {

        this.booksRepo = booksRepo;
    }

    public Book addBook(String id, String name, String content) {

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setContent(Arrays.stream(content.split(" ")).collect(Collectors.toList()));

        return this.booksRepo.create(book);
    }

    public Book getById(String id) {

        return this.booksRepo.get(id);
    }

    public Book update(Book book) {

        return this.booksRepo.update(book);
    }

}

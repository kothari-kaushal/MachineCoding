package com.goodreads.services;

import com.goodreads.dao.Repo;
import com.goodreads.model.Book;
import com.goodreads.model.MetaInfo;
import com.goodreads.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserService {

    private final Repo<User> userRepo;
    private final BookService bookService;

    public UserService(Repo<User> userRepo, BookService bookService) {

        this.userRepo = userRepo;
        this.bookService = bookService;
    }

    public User registerUser(String id, String name) {

        User newUser = new User();

        newUser.setId(id);
        newUser.setName(name);
        newUser.setBooks(new ArrayList<>());
        newUser.setBookIdToMetaInfo(new HashMap<>());

        return this.userRepo.create(newUser);
    }

    public User login(String id) {

        User user = this.userRepo.get(id);
        user.setLoggedIn(true);

        return this.userRepo.update(user);
    }

    public User logout(String id) {

        User user = this.userRepo.get(id);
        user.setLoggedIn(false);

        return this.userRepo.update(user);
    }

    public User purchaseBook(String userId, String bookId) {

        User user = this.userRepo.get(userId);
        Book book = bookService.getById(bookId);

        user.getBooks().add(bookId);
        user.getBookIdToMetaInfo().put(bookId, new MetaInfo());

        return this.userRepo.update(user);
    }

    public String startRead(String userId, String bookId) {

        User user = this.userRepo.get(userId);

        if (!user.getBooks().contains(bookId)) {
            throw new IllegalStateException("first purchase this book");
        }

        Book book = bookService.getById(bookId);
        user.setCurrentBook(bookId);
        userRepo.update(user);

        return book.getContent().get(0);
    }

    public String resumeRead(String userId, String bookId) {

        User user = this.userRepo.get(userId);

        if (!user.getBooks().contains(bookId)) {
            throw new IllegalStateException("first purchase this book");
        }

        user.setCurrentBook(bookId);
        userRepo.update(user);
        Book book = bookService.getById(bookId);
        int currentPage = user.getBookIdToMetaInfo().get(bookId).getCurrentPage();

        return book.getContent().get(currentPage);
    }

    public String rate(String userId, String bookId, float rating) {

        User user = this.userRepo.get(userId);

        if (!user.getBooks().contains(bookId)) {
            throw new IllegalStateException("first purchase this book");
        }

        Book book = bookService.getById(bookId);
        book.setRating(rating);
        this.bookService.update(book);

        user.getBookIdToMetaInfo().get(bookId).setRating(rating);
        userRepo.update(user);

        return book.getContent().get(0);
    }

    public String next(String userId) {

        User user = this.userRepo.get(userId);

        if (user.getCurrentBook() == null) {
            throw new IllegalStateException("select a book first");
        }
        String bookId = user.getCurrentBook();

        Book book = bookService.getById(bookId);
        int currentPage = user.getBookIdToMetaInfo().get(bookId).getCurrentPage();

        user.getBookIdToMetaInfo().get(bookId).setCurrentPage(currentPage + 1);
        userRepo.update(user);

        return book.getContent().get(currentPage + 1);
    }

    public String previous(String userId) {

        User user = this.userRepo.get(userId);

        if (user.getCurrentBook() == null) {
            throw new IllegalStateException("select a book first");
        }
        String bookId = user.getCurrentBook();

        Book book = bookService.getById(bookId);
        int currentPage = user.getBookIdToMetaInfo().get(bookId).getCurrentPage();

        user.getBookIdToMetaInfo().get(bookId).setCurrentPage(currentPage - 1);
        userRepo.update(user);

        return book.getContent().get(currentPage - 1);
    }

    public String goTo(String userId, int pageNum) {

        User user = this.userRepo.get(userId);

        if (user.getCurrentBook() == null) {
            throw new IllegalStateException("select a book first");
        }
        String bookId = user.getCurrentBook();

        Book book = bookService.getById(bookId);

        user.getBookIdToMetaInfo().get(bookId).setCurrentPage(pageNum);
        userRepo.update(user);

        return book.getContent().get(pageNum);
    }

    public List<String> listAllMyBooks(String userId) {

        User user = this.userRepo.get(userId);
        return user.getBooks();
    }

}

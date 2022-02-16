package com.goodreads;

import com.goodreads.dao.inmemory.BookRepo;
import com.goodreads.dao.inmemory.UserRepo;
import com.goodreads.services.BookService;
import com.goodreads.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        BookService bookService = new BookService(new BookRepo());
        UserService userService = new UserService(new UserRepo(), bookService);

        while (true) {
            try {
                String input = BUFFERED_READER.readLine();
                String[] inputParams = input.split(" ");
                if (inputParams.length < 2) {
                    throw new IllegalStateException("atleast 2 args are required");
                }
                Command command = Command.from(inputParams[0]);
                String userId = inputParams[1];

                switch (command) {
                    case ADD:
                        String bookId = inputParams[1];
                        System.out.println("Please enter content");
                        String content = BUFFERED_READER.readLine();
                        bookService.addBook(bookId, bookId, content);
                        System.out.println("book added successfully");
                        break;
                    case REGISTER:
                        userService.registerUser(userId, userId);
                        System.out.println("user registered successfully");
                        break;
                    case LOGIN:
                        userService.login(userId);
                        System.out.println("user logged in successfully");
                        break;
                    case LOGOUT:
                        userService.logout(userId);
                        System.out.println("Sad to see you go. See you later!!");
                        break;
                    case PURCHASE:
                        userService.purchaseBook(userId, inputParams[2]);
                        break;
                    case NEXT:
                        String next = userService.next(userId);
                        System.out.println(next);
                        break;
                    case PREV:
                        String prev = userService.previous(userId);
                        System.out.println(prev);
                        break;
                    case GOTO:
                        String future = userService.goTo(userId, Integer.parseInt(inputParams[2]));
                        System.out.println(future);
                        break;
                    case RATE:
                        userService.rate(userId, inputParams[2], Float.parseFloat(inputParams[3]));
                        break;
                    case LIST:
                        userService.listAllMyBooks(userId);
                        break;
                    case START_READ:
                        String read = userService.startRead(userId, inputParams[2]);
                        System.out.println(read);
                        break;
                    case RESUME:
                        String resume = userService.resumeRead(userId, inputParams[2]);
                        System.out.println(resume);
                        break;
                    default:
                        System.out.println("command not supported.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

}

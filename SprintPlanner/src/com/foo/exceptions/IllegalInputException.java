package com.foo.exceptions;

public class IllegalInputException extends RuntimeException {

    public IllegalInputException(String message) {

        super(message);
    }

    public IllegalInputException(String message, Throwable cause) {

        super(message, cause);
    }
}

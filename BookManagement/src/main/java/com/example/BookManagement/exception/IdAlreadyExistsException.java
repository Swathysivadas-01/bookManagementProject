package com.example.BookManagement.exception;


public class IdAlreadyExistsException extends Exception{
    private String message;

    public IdAlreadyExistsException() {
    }

    public IdAlreadyExistsException(String message) {
        super();
        this.message = message;
    }
}

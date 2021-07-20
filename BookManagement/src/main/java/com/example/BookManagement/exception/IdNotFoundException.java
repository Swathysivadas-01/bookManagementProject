package com.example.BookManagement.exception;

public class IdNotFoundException extends Exception{
    private String message;

    public IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super();
        this.message = message;
    }
}

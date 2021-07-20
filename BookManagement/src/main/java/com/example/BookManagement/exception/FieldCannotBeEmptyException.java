package com.example.BookManagement.exception;

public class FieldCannotBeEmptyException extends Exception{
    private String message;

    public FieldCannotBeEmptyException() {
    }

    public FieldCannotBeEmptyException(String message) {
        super();
        this.message = message;
    }
}

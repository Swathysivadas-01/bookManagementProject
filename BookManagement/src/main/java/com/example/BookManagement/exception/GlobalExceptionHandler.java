package com.example.BookManagement.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;

    @Value(value = "${data.exception.message2}")
    private String message2;

    @Value(value = "${data.exception.message3}")
    private String message3;

    @ExceptionHandler(value = IdAlreadyExistsException.class)
    public ResponseEntity<String> UserAlreadyExistsException(IdAlreadyExistsException idAlreadyExistsException){
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<String> UserNotFoundException(IdNotFoundException idNotFoundException){
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = FieldCannotBeEmptyException.class)
    public ResponseEntity<String> FieldCannotBeEmpty(FieldCannotBeEmptyException fieldCannotBeEmptyException){
        return new ResponseEntity<String>(message3, HttpStatus.CONFLICT);
    }
}

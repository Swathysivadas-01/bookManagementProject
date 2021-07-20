package com.example.BookManagement.service;

import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.model.request.BookRequest;
import com.example.BookManagement.model.response.BookResponse;

import java.util.List;
import java.util.Optional;

public interface BookServiceDAO {
    //method for find all books
    List<BookResponse> findAll();
    //method for find book by id
    Optional<BookResponse> findById(int id);
    //method for save  book
    Book saveBook(BookRequest bookRequest) throws IdAlreadyExistsException, FieldCannotBeEmptyException;
    //method for update books
    Book updateBook(int id,BookRequest bookRequest)throws IdNotFoundException;
    //method for delete book
    Book deleteBook(int id) throws IdNotFoundException;


}

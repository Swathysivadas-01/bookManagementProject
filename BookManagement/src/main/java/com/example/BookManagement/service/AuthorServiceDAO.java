package com.example.BookManagement.service;

import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.model.response.BookResponse;

import java.util.List;
import java.util.Optional;

public interface AuthorServiceDAO {
    //method for find all authors
    List<AuthorResponse> findAllAuthor();
    //method for find all author by id
    Optional<AuthorResponse> findById(int id);
    //method for find books of an author
    List<Book> getBooksByAuthor(int id);
    //method for save author
    Author saveAuthor(AuthorRequest authorRequest) throws IdAlreadyExistsException, FieldCannotBeEmptyException;
    //method for update author
    Author updateAuthor(int id,AuthorRequest authorRequest)throws IdNotFoundException;
    //method for delete author
    Author deleteAuthor(int id) throws IdNotFoundException;
    //method for finding count
    int findAuthorCount();

    int findBooksCountByAuthor(int id);

    List<AuthorResponse> findSortedAuthorList();

    List<AuthorResponse> findReversedSortedAuthorList();


    List<AuthorResponse> findAuthorByCountry(String country);
}

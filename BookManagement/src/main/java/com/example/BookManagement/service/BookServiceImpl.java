package com.example.BookManagement.service;


import com.example.BookManagement.entity.Author;
import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.model.request.BookRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.model.response.BookResponse;
import com.example.BookManagement.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//@Service indicates annotated class is a service which hold business logic in the Service layer
@Service
public class BookServiceImpl implements BookServiceDAO{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    ModelMapper mapper;

    //Implementation of findAll method
    @Override
    public List<BookResponse> findAll() {
        List<BookResponse> responses = new ArrayList<>();
        Iterable<Book> books = bookRepository.findAll();
        if (books!=null){
            responses = mapper.map(books,List.class);
        }
        return responses;
    }

    //Implementation of findById method
    @Override
    public Optional<BookResponse> findById(int id) {
        Optional<BookResponse> response = mapper.map(bookRepository.findById(id),Optional.class);
        return response;
    }


    //Implementation of saveBook method
    @Override
    public Book saveBook(BookRequest bookRequest) throws IdAlreadyExistsException, FieldCannotBeEmptyException {
        if (bookRequest.getTitle()==null||bookRequest.getTitle()==""||bookRequest.getPublisher()==null||bookRequest.getPublisher()==""||bookRequest.getPrice()<=0||bookRequest.getBook_id()<=0){
            throw new FieldCannotBeEmptyException();
        }
       else if (bookRepository.existsById(bookRequest.getBook_id())){
           throw new IdAlreadyExistsException();
       }
       Book book = mapper.map(bookRequest,Book.class);
       return bookRepository.save(book);
    }

    //Implementation of updateBook method
    @Override
    public Book updateBook(int id, BookRequest bookRequest) throws IdNotFoundException {
        if (!bookRepository.existsById(bookRequest.getBook_id())){
            throw new IdNotFoundException();
        }
        Book book = mapper.map(bookRequest,Book.class);
        return bookRepository.save(book);
    }


    //Implementation of deleteBook method
    @Override
    public Book deleteBook(int id) throws IdNotFoundException {
        if (!bookRepository.existsById(id)){
            throw new IdNotFoundException();
        }
        Book book =  bookRepository.findById(id).get();
        bookRepository.deleteById(id);
        return book ;
    }


}

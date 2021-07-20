package com.example.BookManagement.controller;

import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.model.request.BookRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.model.response.BookResponse;
import com.example.BookManagement.service.BookServiceDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookServiceDAO bookService;

    //End point get all books
    @GetMapping("/books")
    public List<BookResponse> findAll(){
        return bookService.findAll();
    }

    //End point for get specific book
    @GetMapping("/get/book/{id}")
    public Optional<BookResponse> findById(@PathVariable int id){
        return bookService.findById(id);
    }


    //End point for save book
    @PostMapping("/book")
    public ResponseEntity<?> saveBook(@RequestBody BookRequest bookRequest) throws IdAlreadyExistsException, FieldCannotBeEmptyException {

        return new ResponseEntity<>(bookService.saveBook(bookRequest), HttpStatus.CREATED);
    }

    //End point for updating books
    @PutMapping("/{id}/book")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody BookRequest bookRequest) throws IdNotFoundException {
        return new ResponseEntity<>(bookService.updateBook(id,bookRequest), HttpStatus.OK);
    }
    //End point for deleting books
    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) throws IdNotFoundException {

       return new ResponseEntity<>( bookService.deleteBook(id),HttpStatus.OK);

    }
}

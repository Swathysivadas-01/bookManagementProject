package com.example.BookManagement.controller;

import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.service.AuthorServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//RestController annotation is used to create Restful web services
@RestController
//RequestMapping annotation maps HTTP requests to handler methods
@RequestMapping("/api/v1")
public class AuthorControllerTwo {
    private AuthorServiceDAO authorService;
    @Autowired
    public AuthorControllerTwo(@Qualifier("authorServiceTwo") AuthorServiceDAO authorService) {
        this.authorService = authorService;
    }
    //End point for adding an author
    @PostMapping("/second/author")
    public ResponseEntity<?> saveAuthor(@RequestBody AuthorRequest authorRequest) throws IdAlreadyExistsException, FieldCannotBeEmptyException {
        return new ResponseEntity<>(authorService.saveAuthor(authorRequest),HttpStatus.OK);
    }
    //End point for retrieving all authors
    @GetMapping("/second/authors")
    public List<AuthorResponse> findAll(){
        return authorService.findAllAuthor();
    }
    //End point for updating authors
    @PutMapping("/{id}/second/author")
    public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody AuthorRequest authorRequest) throws IdNotFoundException {

        return new ResponseEntity<>(authorService.updateAuthor(id,authorRequest), HttpStatus.OK);

    }
    //End point for retrieving author of specific id
    @GetMapping("/second/author/{id}")
    public Optional<AuthorResponse> findById(@PathVariable int id){
        return authorService.findById(id);
    }


    //End point for deleting authors
    @DeleteMapping("/{id}/second/author")
    public ResponseEntity<?> deleteAuthor(@PathVariable int id) throws IdNotFoundException {

        return new ResponseEntity<>(authorService.deleteAuthor(id),HttpStatus.OK);

    }

}

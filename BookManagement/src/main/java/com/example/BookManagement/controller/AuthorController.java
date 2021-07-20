package com.example.BookManagement.controller;

import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.model.response.BookResponse;
import com.example.BookManagement.service.AuthorServiceDAO;
import lombok.AllArgsConstructor;

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
public class AuthorController {

    private AuthorServiceDAO authorService;
    @Autowired
    public AuthorController(@Qualifier("authorServiceOne") AuthorServiceDAO authorService) {
        this.authorService = authorService;
    }

    //End point for retrieving all authors count
    @GetMapping("sort/desc/authors")
    public List<AuthorResponse> findSortedAuthorList(){
        return authorService.findSortedAuthorList();
    }
    @GetMapping("sort/asc/authors")
    public List<AuthorResponse> findReversedSortedAuthorList(){
        return authorService.findReversedSortedAuthorList();
    }
    @GetMapping("{country}/authors")
    public List<AuthorResponse> findAuthorByCountry(@PathVariable String country){
        return authorService.findAuthorByCountry(country);
    }

    //End point for retrieving all authors count
    @GetMapping("/count/authors")
    public int findAuthorCount(){
        return authorService.findAuthorCount();
    }

    //End point for retrieving count of books of specific author
    @GetMapping("/books/count/{id}")
    public int findBooksCountByAuthor(@PathVariable int id){
        return authorService.findBooksCountByAuthor(id);
    }

    //End point for retrieving all authors
    @GetMapping("/authors")
    public List<AuthorResponse> findAll(){
        return authorService.findAllAuthor();
    }


    //End point for retrieving author of specific id
    @GetMapping("/author/{id}")
    public Optional<AuthorResponse> findById(@PathVariable int id){
        return authorService.findById(id);
    }

    //End point for retrieving books of specific author
    @GetMapping("/author/{id}/books")
    public List<Book> getBookByAuthor(@PathVariable int id){
        return authorService.getBooksByAuthor(id);
    }

    //End point for adding an author
    @PostMapping("/author")
    public ResponseEntity<?> saveAuthor(@RequestBody AuthorRequest authorRequest) throws IdAlreadyExistsException, FieldCannotBeEmptyException {
        return new ResponseEntity<>(authorService.saveAuthor(authorRequest),HttpStatus.OK);
    }

    //End point for updating authors
    @PutMapping("/{id}/author")
    public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody AuthorRequest authorRequest) throws IdNotFoundException {

        return new ResponseEntity<>(authorService.updateAuthor(id,authorRequest), HttpStatus.OK);

    }

    //End point for deleting authors
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable int id) throws IdNotFoundException {

       return new ResponseEntity<>(authorService.deleteAuthor(id),HttpStatus.OK);

    }
}

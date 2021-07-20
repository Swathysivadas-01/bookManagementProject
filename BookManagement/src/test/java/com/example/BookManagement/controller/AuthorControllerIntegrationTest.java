package com.example.BookManagement.controller;

import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.request.BookRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.model.response.BookResponse;
import com.example.BookManagement.service.AuthorServiceDAO;
import com.example.BookManagement.service.AuthorServiceImpl;
import com.example.BookManagement.service.BookServiceDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AuthorControllerIntegrationTest {
//    @Autowired
//    private AuthorServiceDAO authorServiceDAO;
//
    @Autowired
    @Qualifier("authorServiceOne")
    private AuthorServiceDAO authorService;

    private Author author;
    private List<Author> authorList;
    private AuthorRequest authorRequest;
    private List<AuthorRequest> authorRequestList;
    private AuthorRequest authorRequest1;


    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setAuthor_id(1);
        author.setName("swathy");
        author.setCountry("india");

        authorList = new ArrayList<>();
        authorList.add(author);
    }

    @AfterEach
    public void tearDown() {
        author = null;
    }

    @Test
    public void givenAuthorThenSave() throws IdAlreadyExistsException, FieldCannotBeEmptyException {
        authorRequest = new AuthorRequest();
        authorRequest.setAuthor_id(1);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");

        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        Author savedAuthor = authorService.saveAuthor(authorRequest);
        assertNotNull(savedAuthor);
        assertEquals(author.getAuthor_id(), savedAuthor.getAuthor_id());
    }

    @Test
    public void toFindAllAuthors()  {
        List<AuthorResponse> authorResponseList = authorService.findAllAuthor();
        assertNotNull(authorResponseList);
    }
    @Test
    public void givenAuthorIdThenShouldReturnRespectiveAuthor() throws Exception {
            authorRequest = new AuthorRequest();
            authorRequest.setAuthor_id(2);
            authorRequest.setName("swathy");
            authorRequest.setCountry("india");

            authorRequestList = new ArrayList<>();
            authorRequestList.add(authorRequest);
        assertNotNull(authorService.saveAuthor(authorRequest));
        Optional<AuthorResponse> retrievedAuthor = authorService.findById(authorRequest.getAuthor_id());
        assertNotNull(retrievedAuthor);
    }

    @Test
    void givenAuthorToDeleteThenShouldReturnTheDeletedAuthor() throws Exception {
        authorRequest=new AuthorRequest();
        authorRequest.setAuthor_id(1);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");
        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        assertNotNull(authorService.saveAuthor(authorRequest));
        Author deletedAuthor = authorService.deleteAuthor(authorRequest.getAuthor_id());
        assertNotNull(deletedAuthor);
    }
    @Test
    void givenAuthorToFindCount() throws Exception {
        authorRequest=new AuthorRequest();
        authorRequest.setAuthor_id(6);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");
        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        int count = authorService.findAuthorCount();
        assertNotNull(count);
       // assertNotNull(count);
    }
    @Test
    void givenAuthorToReverseSort() {
        authorRequest=new AuthorRequest();
        authorRequest.setAuthor_id(1);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");
        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        List<AuthorResponse> reverseSortedAuthor = authorService.findReversedSortedAuthorList();
        assertNotNull(reverseSortedAuthor);
    }
    @Test
    void givenAuthorToSort() {
        authorRequest=new AuthorRequest();
        authorRequest.setAuthor_id(1);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");
        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        List<AuthorResponse> SortedAuthor = authorService.findSortedAuthorList();
        assertNotNull(SortedAuthor);
    }

    @Test
    void findBookByAuthor() throws Exception {
        authorRequest=new AuthorRequest();
        authorRequest.setAuthor_id(1);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");
        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        assertNotNull(authorService.getBooksByAuthor(1));
        List<Book> authorBook = authorService.getBooksByAuthor(authorRequest.getAuthor_id());
        assertNotNull(authorBook);
    }
    @Test
    void testFindAuthorByCountry() throws Exception {
        authorRequest=new AuthorRequest();
        authorRequest.setAuthor_id(1);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");
        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        assertNotNull(authorService.findAuthorByCountry("india"));
        List<Book> authorBook = authorService.getBooksByAuthor(authorRequest.getAuthor_id());
        assertNotNull(authorBook);
    }
    @Test
    void testFindBooksCountByAuthor() throws Exception {
        authorRequest=new AuthorRequest();
        authorRequest.setAuthor_id(6);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");
        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        int count = authorService.findBooksCountByAuthor(1);
        assertNotNull(count);
        // assertNotNull(count);
    }
    @Test
    public void testUpdateAuthor() throws IdNotFoundException, IdAlreadyExistsException, FieldCannotBeEmptyException {
        authorRequest = new AuthorRequest();
        authorRequest.setAuthor_id(19);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");

        authorRequestList = new ArrayList<>();
        authorRequestList.add(authorRequest);
        authorService.saveAuthor(authorRequest);
        Author updatedAuthor = authorService.updateAuthor(19,authorRequest);
        assertNotNull(updatedAuthor);
        //assertEquals(author.getAuthor_id(), updatedAuthor.getAuthor_id());
    }
}

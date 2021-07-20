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
import com.example.BookManagement.service.BookServiceDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookControllerIntegrationTest
{
    @Autowired
    private BookServiceDAO bookServiceDAO;
    private Book book;
    private List<Book> bookList;
    private BookRequest bookRequest;
    private List<BookRequest> bookRequestList;



    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setBook_id(1);
        book.setTitle("java");
        book.setPublisher("DPB");
        book.setPrice(100);

        bookList = new ArrayList<>();
       bookList.add(book);
    }

    @AfterEach
    public void tearDown() {
        book = null;
    }

    @Test
    void givenBookToSave() throws IdAlreadyExistsException, FieldCannotBeEmptyException {
        bookRequest = new BookRequest();
        bookRequest.setBook_id(1);
        bookRequest.setTitle("java");
        bookRequest.setPublisher("DPB");
        bookRequest.setPrice(100);

        bookRequestList = new ArrayList<>();
        bookRequestList.add(bookRequest);
        Book savedBook = bookServiceDAO.saveBook(bookRequest);
        assertNotNull(savedBook);
        assertEquals(book.getBook_id(), savedBook.getBook_id());
    }

    @Test
    public void toFindAllBooks()  {
        List<BookResponse> departmentList = bookServiceDAO.findAll();
        assertNotNull(bookList);
    }

    @Test
    void givenBookToDeleteThenShouldReturnTheDeletedBook() throws Exception {
        bookRequest=new BookRequest();
        bookRequest.setBook_id(3);
        bookRequest.setTitle("java");
        bookRequest.setPublisher("DPB");
        bookRequest.setPrice(100);
        bookRequestList = new ArrayList<>();
        bookRequestList.add(bookRequest);
        assertNotNull(bookServiceDAO.saveBook(bookRequest));
        Book deletedBook = bookServiceDAO.deleteBook(bookRequest.getBook_id());
        assertNotNull(deletedBook);
    }

    @Test
    public void givenBookIdThenShouldReturnRespectiveBook() throws Exception {
        bookRequest=new BookRequest();
        bookRequest.setBook_id(2);
        bookRequest.setTitle("java");
        bookRequest.setPublisher("DPB");
        bookRequest.setPrice(100);
        bookRequestList = new ArrayList<>();
        bookRequestList.add(bookRequest);
        assertNotNull(bookServiceDAO.saveBook(bookRequest));
        Optional<BookResponse> retrievedBook = bookServiceDAO.findById(bookRequest.getBook_id());
        assertNotNull(retrievedBook);
    }
    @Test
    public void testUpdateBook() throws IdNotFoundException, IdAlreadyExistsException, FieldCannotBeEmptyException {
        bookRequest=new BookRequest();
        bookRequest.setBook_id(19);
        bookRequest.setTitle("java");
        bookRequest.setPublisher("DPB");
        bookRequest.setPrice(100);
        bookRequestList = new ArrayList<>();
        bookRequestList.add(bookRequest);
        bookServiceDAO.saveBook(bookRequest);
        Book updatedBook = bookServiceDAO.updateBook(19,bookRequest);
        assertNotNull(updatedBook);
        //assertEquals(author.getAuthor_id(), updatedAuthor.getAuthor_id());
    }

}







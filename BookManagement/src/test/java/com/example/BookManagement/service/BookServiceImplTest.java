package com.example.BookManagement.service;

import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.request.BookRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.model.response.BookResponse;
import com.example.BookManagement.repository.AuthorRepository;
import com.example.BookManagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookServiceImpl service;

    @MockBean
    private BookRepository repository;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Book book;
    @Mock
    private BookRequest request;
    @Mock
    private BookResponse response;
    @Mock
    private Author author;

    List<Book> books = new ArrayList<>();
    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setBook_id(1);
        book.setPublisher("DPB");
        book.setPrice(456);
        book.setTitle("java");

        author = new Author();
        author.setAuthor_id(1);
        author.setCountry("india");
        author.setName("swathy");
        book.setAuthor(author);
        books.add(book);

    }


    @Test
    void testListOfBooks()throws Exception {

        List<Book> list = new ArrayList<>();
        list.add(book);
        Mockito.when(repository.findAll()).thenReturn(list);
        assertTrue(list.size()==1);

    }

    @Test
    void testDeleteBook() throws Exception {
        Mockito.when(repository.existsById(book.getBook_id())).thenReturn(false);
        Exception notFoundException = assertThrows(Exception.class, () -> service.deleteBook(book.getBook_id()));
        assertNull(notFoundException.getMessage(),"Id Not found!!");
    }
    @Test
    void testUpdateBook() throws Exception {
        Mockito.when(repository.existsById(Mockito.anyInt())).thenReturn(false);
        IdNotFoundException notFoundException = assertThrows(IdNotFoundException.class, () -> service.updateBook(book.getBook_id(), request));
        assertNull(notFoundException.getMessage(),"Id Not found!!");
        Mockito.when(repository.save(book)).thenReturn(book);
        assertEquals(book, repository.save(book));


    }
    @Test
    public void saveBookTest() throws Exception {
//        Mockito.when(request.getBook_id()).thenReturn(0);
//        Mockito.when(request.getTitle()).thenReturn(null);
//        Mockito.when(request.getPrice()).thenReturn(0);
//        Mockito.when(request.getPublisher()).thenReturn(null);
//        FieldCannotBeEmptyException fieldCannotBeEmpty = assertThrows(FieldCannotBeEmptyException.class, () -> service.saveBook(request));
//        assertNull(fieldCannotBeEmpty.getMessage(), "Field cannot be empty!!");

        Mockito.when(repository.existsById(author.getAuthor_id())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> service.saveBook(request));
        assertNull(exception.getMessage(), "Field cannot be empty!!");

        Mockito.when(repository.save(book)).thenReturn(book);
        assertEquals(book, repository.save(book));

    }

}
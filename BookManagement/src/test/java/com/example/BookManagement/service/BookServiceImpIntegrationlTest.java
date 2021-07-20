package com.example.BookManagement.service;

import com.example.BookManagement.entity.Book;
import com.example.BookManagement.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

//import static jdk.internal.vm.compiler.word.LocationIdentity.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImpIntegrationlTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;
    private Book book,book1,book2;
    private List<Book> bookList;
    private Optional optional;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        book = new Book();
        book.setBook_id(1);
        book.setTitle("java");
        book.setPublisher("DPB");
        book.setPrice(100);
        bookRepository.save(book);
        book1 = new Book();
        optional = Optional.of(book);
    }
    @AfterEach
    public void tearDown() {
        book = null;
    }

    @Test
    public void givenBookToSaveThenReturnSavedBook() {
        Book savedBook = bookRepository.save(book);
        assertNotNull(savedBook);
        assertEquals(book.getBook_id(), savedBook.getBook_id());
    }

//    @Test
//    public void GivenGetAllBookThenReturnListOfBooks() {
//        List<Book> bookList = (List<Book>) bookRepository.findAll();
//        assertNotNull(bookList);
//    }

//    @Test
//    public void givenBookToSaveThenShouldReturnSavedBook() {
//        when(bookRepository.save(any())).thenReturn(book);
//        assertEquals(book, blogService.saveBook(book));
//        verify(bookRepository, times(1)).save(any());
//    }

}
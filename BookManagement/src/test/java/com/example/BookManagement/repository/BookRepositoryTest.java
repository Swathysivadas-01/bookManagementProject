package com.example.BookManagement.repository;

import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    private Book book;
    private  Book book1;


    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setBook_id(100);
        book.setTitle("java");
        book.setPublisher("swathy");
        book.setPrice(90);
    }
    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
        book = null;
    }
    @Test
    public void givenBookToSaveThenShouldReturnSavedBook() {
        bookRepository.save(book);
        Book fetchedBook = bookRepository.findById(book.getBook_id()).get();
        assertEquals(100, fetchedBook.getBook_id());
    }

    @Test
    public void givenGetAllAuthorsThenShouldReturnListOfAllAuthors() {

        book = new Book();
        book.setBook_id(1);
        book.setTitle("java");
        book.setPublisher("swathy");
        book.setPrice(90);
        bookRepository.save(book);
        book1 = new Book();
        book1.setBook_id(2);
        book1.setTitle("java");
        book1.setPublisher("swathy");
        book1.setPrice(90);
        bookRepository.save(book1);

        List<Book> bookList = (List<Book>) bookRepository.findAll();
        assertEquals("java", bookList.get(1).getTitle());
    }
    @Test
    public void givenAuthorIdThenShouldReturnRespectiveAuthor() {

        book.setBook_id(1);
        book.setTitle("java");
        book.setPublisher("swathy");
        book.setPrice(90);
        Book book2= bookRepository.save(book);
        Optional<Book> optional = bookRepository.findById(book2.getBook_id());
        assertEquals(book2.getBook_id(), optional.get().getBook_id());
        assertEquals(book2.getTitle(), optional.get().getTitle());
        assertEquals(book2.getPublisher(), optional.get().getPublisher());
        assertEquals(book2.getPrice(), optional.get().getPrice());

    }

    @Test
    public void givenAuthorIdToDeleteThenShouldReturnDeletedAuthor() {

        book = new Book();
        book.setBook_id(1);
        book.setTitle("java");
        book.setPublisher("swathy");
        book.setPrice(90);
        bookRepository.save(book);
        bookRepository.deleteById(book.getBook_id());
        Optional optional = bookRepository.findById(book.getBook_id());
        assertEquals(Optional.empty(), optional);
    }





}
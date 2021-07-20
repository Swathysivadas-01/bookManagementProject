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
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    private Author author;
    private Author author1;


    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setAuthor_id(100);
        author.setName("swathy");
        author.setCountry("india");
    }
    @AfterEach
    public void tearDown() {
        authorRepository.deleteAll();
        author = null;
    }
    @Test
    public void givenBookToSaveThenShouldReturnSavedBook() {
        authorRepository.save(author);
        Author fetchedAuthor = authorRepository.findById(author.getAuthor_id()).get();
        assertEquals(100, fetchedAuthor.getAuthor_id());
    }
    @Test
    public void givenGetAllAuthorsThenShouldReturnListOfAllAuthors() {

        author = new Author();
        author.setAuthor_id(1);
        author.setName("swathy");
        author.setCountry("india");

        authorRepository.save(author);
        author1 = new Author();
        author1.setAuthor_id(2);
        author1.setName("aswathy");
        author1.setCountry("india");
        authorRepository.save(author1);

        List<Author> authorList = (List<Author>) authorRepository.findAll();
        assertEquals("aswathy", authorList.get(1).getName());
    }

    @Test
    public void givenAuthorIdThenShouldReturnRespectiveAuthor() {

        author = new Author();
        author.setAuthor_id(1);
        author.setName("swathy");
        author.setCountry("india");
        Author author2= authorRepository.save(author);
        Optional<Author> optional = authorRepository.findById(author2.getAuthor_id());
        assertEquals(author2.getAuthor_id(), optional.get().getAuthor_id());
        assertEquals(author2.getName(), optional.get().getName());
        assertEquals(author2.getCountry(), optional.get().getCountry());

    }

    @Test
    public void givenAuthorIdToDeleteThenShouldReturnDeletedAuthor() {

        author = new Author();
        author.setAuthor_id(1);
        author.setName("swathy");
        author.setCountry("india");
        authorRepository.save(author);
        authorRepository.deleteById(author.getAuthor_id());
        Optional optional = authorRepository.findById(author.getAuthor_id());
        assertEquals(Optional.empty(), optional);
    }
}
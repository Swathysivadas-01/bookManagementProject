package com.example.BookManagement.service;

import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
class AuthorServiceImplTest {
    @Autowired
    private AuthorServiceImpl service;

    @MockBean
    private AuthorRepository repository;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Author author;
    @Mock
    private AuthorRequest request;
    @Mock
    private AuthorResponse response;
    @Mock
    private Book book;


    @BeforeEach
    public void setUp(){
        author = new Author();
        author.setAuthor_id(1);
        author.setCountry("india");
        author.setName("swathy");

    }

    @Test
    public void saveAuthorTest() throws Exception{
//        Mockito.when(request.getAuthor_id()).thenReturn(0);
//        Mockito.when(request.getCountry()).thenReturn(null);
//        Mockito.when(request.getName()).thenReturn(null);
//        FieldCannotBeEmptyException fieldCannotBeEmpty = assertThrows(FieldCannotBeEmptyException.class, () -> service.saveAuthor(request));
//        assertNull(fieldCannotBeEmpty.getMessage(), "Field cannot be empty!!");

        Mockito.when(repository.existsById(author.getAuthor_id())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> service.saveAuthor(request));
        assertNull(exception.getMessage(), "Field cannot be empty!!");
        Mockito.when(repository.save(author)).thenReturn(author);
        assertEquals(author, repository.save(author));

    }
    @Test
    void testListOfAuthors()throws Exception {

        List<Author> list = new ArrayList<>();
        list.add(author);
        Mockito.when(repository.findAll()).thenReturn(list);
        assertTrue(list.size()==1);

    }
//    @Test
//    public void findAuthorCountTest(){
//        //Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(author));
//        List<Author> list = new ArrayList<>();
//        list.add(author);
//        Mockito.when(repository.findAll()).thenReturn((list));
//        assertNotNull(list.size());
//        //assertTrue(list.size()>0);
//    }
//    @Test
//    public void findBookCountByAuthorTest(){
//        book=new Book();
//        book.setBook_id(12);
//
//        Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(author));
//        List<Author> list = new ArrayList<>();
//        list.add(author);
//        Mockito.when(author.getBook()).thenReturn((List<Book>) book);
//        assertTrue(list.size()>0);
//    }

    @Test
    void testDeleteAuthor() throws IdNotFoundException {

        Mockito.when(repository.existsById(author.getAuthor_id())).thenReturn(false);
        Exception notFoundException = assertThrows(Exception.class, () -> service.deleteAuthor(author.getAuthor_id()));
        assertNull(notFoundException.getMessage(),"Id Not found!!");
    }
    @Test
    void testUpdateAuthor() throws IdNotFoundException {
        Mockito.when(repository.existsById(Mockito.anyInt())).thenReturn(false);
        IdNotFoundException notFoundException = assertThrows(IdNotFoundException.class, () -> service.updateAuthor(author.getAuthor_id(), request));
        assertNull(notFoundException.getMessage(),"Id Not found!!");
        Mockito.when(repository.save(author)).thenReturn(author);
        assertEquals(author, repository.save(author));

    }


}
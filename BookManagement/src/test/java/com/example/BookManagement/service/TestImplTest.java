//package com.example.BookManagement.service;
//
//import com.example.BookManagement.entity.Author;
//import com.example.BookManagement.exception.FieldCannotBeEmptyException;
//import com.example.BookManagement.exception.IdAlreadyExistsException;
//import com.example.BookManagement.exception.IdNotFoundException;
//import com.example.BookManagement.model.request.AuthorRequest;
//import com.example.BookManagement.model.response.AuthorResponse;
//import com.example.BookManagement.repository.AuthorRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//@RunWith(SpringRunner.class)
//class TestImplTest {
//    @Autowired
//    private TestImpl service;
//
//    @MockBean
//    private AuthorRepository repository;
//
//    @Mock
//    private ModelMapper modelMapper;
//    @Mock
//    private Author author;
//    @Mock
//    private AuthorRequest request;
//    @Mock
//    private AuthorResponse response;
//
//
//    @BeforeEach
//    public void setUp(){
//        author = new Author();
//        author.setAuthor_id(1);
//        author.setCountry("india");
//        author.setName("swathy");
//
//    }
//
//
//    @Test
//    public void saveAuthorTest() throws IdAlreadyExistsException ,FieldCannotBeEmptyException{
//        Mockito.when(request.getAuthor_id()).thenReturn(0);
//        Mockito.when(request.getCountry()).thenReturn(null);
//        Mockito.when(request.getName()).thenReturn(null);
//        FieldCannotBeEmptyException fieldCannotBeEmpty = assertThrows(FieldCannotBeEmptyException.class, () -> service.saveAuthor(request));
//        assertNull(fieldCannotBeEmpty.getMessage(), "Field cannot be empty!!");
//
////        Mockito.when(repository.existsById(author.getAuthor_id())).thenReturn(true);
////        IdAlreadyExistsException idAlreadyExistsException = assertThrows(IdAlreadyExistsException.class, () -> service.saveAuthor(request));
////        assertEquals(idAlreadyExistsException.getMessage(), "Id Already exists!!");
//
//        Mockito.when(repository.save(author)).thenReturn(author);
//        assertEquals(author, repository.save(author));
//
//    }
//    @Test
//    void testListOfAuthors()throws Exception {
//        // Mockito.when(repository.findAll()).thenReturn(null);
////        Exception notFoundException = assertThrows(NotFoundException.class, () -> service.listOfModels());
////        assertEquals(notFoundException.getMessage(), "No Records Found");
//        //Model model=new Model(1, "A4", "sedan");
//        List<Author> list = new ArrayList<>();
//        list.add(author);
//        Mockito.when(repository.findAll()).thenReturn(list);
//        assertTrue(list.size()==1);
//
//    }
//
//    @Test
//    void testDeleteAuthor() throws IdNotFoundException {
//
//        Mockito.when(repository.existsById(author.getAuthor_id())).thenReturn(false);
//        Exception notFoundException = assertThrows(Exception.class, () -> service.deleteAuthor(author.getAuthor_id()));
//        assertNull(notFoundException.getMessage(),"Id Not found!!");
//    }
//    @Test
//    void testUpdateAuthor() throws IdNotFoundException {
//        Mockito.when(repository.existsById(Mockito.anyInt())).thenReturn(false);
//        IdNotFoundException notFoundException = assertThrows(IdNotFoundException.class, () -> service.updateAuthor(author.getAuthor_id(), request));
//        assertNull(notFoundException.getMessage(),"Id Not found!!");
//        Mockito.when(repository.save(author)).thenReturn(author);
//        assertEquals(author, repository.save(author));
//
////		Mockito.when(repository.existsById(Mockito.anyInt())).thenReturn(false);
////		ModelRequest request = new ModelRequest(1, "A4", "sedan", "some text");
////		Exception notFoundException = assertThrows(NotFoundException.class, () -> service.updateModel(Mockito.any(), request));
////		assertEquals(notFoundException.getMessage(), "Model not found with id: "+Mockito.anyInt());
//
//    }
//
//}
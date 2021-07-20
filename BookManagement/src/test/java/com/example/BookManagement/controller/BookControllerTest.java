package com.example.BookManagement.controller;

import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.request.BookRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.model.response.BookResponse;
import com.example.BookManagement.service.AuthorServiceDAO;
import com.example.BookManagement.service.BookServiceDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookControllerTest {
    private MockMvc mockMvc;
    @Mock
    BookServiceDAO bookService;
    @InjectMocks
    private BookController bookController;

    private Book book;
    private List<Book> bookList1;
    private BookResponse bookResponse;
    private List<BookResponse> bookList;
    private BookRequest bookRequest;
    private List<BookRequest> bookList2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        book = new Book();
        book.setTitle("java");
        book.setPublisher("DPB");
        book.setPrice(100);

        bookList1 = new ArrayList<>();
        bookList1.add(book);
    }

    @AfterEach
    public void tearDown() {
        book = null;
    }
    @Test
    public void givenBookToSaveThenReturnSavedBook() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        bookRequest = new BookRequest();
        bookRequest.setTitle("java");
        bookRequest.setPublisher("DPB");
        bookRequest.setPrice(100);

        bookList2 = new ArrayList<>();
        bookList2.add(bookRequest);

        when(bookService.saveBook(any())).thenReturn(book);
        mockMvc.perform(post("/api/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(book)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(bookService).saveBook(any());
    }
    @Test
    public void givenGetAllBooksThenShouldReturnListOfAllBooks() throws Exception {
        when(bookService.findAll()).thenReturn(bookList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
                .andDo(MockMvcResultHandlers.print());
        verify(bookService).findAll();
        verify(bookService, times(1)).findAll();

    }
    @Test
    public void givenBookIdToDeleteThenShouldNotReturnDeletedBook() throws Exception {
        when(bookService.deleteBook(book.getBook_id())).thenReturn(book);
        mockMvc.perform(delete("/api/v1/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    void givenBookIdThenShouldReturnRespectiveBook() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        bookResponse = new BookResponse();
        bookResponse.setBook_id(1);
        bookResponse.setTitle("java");
        bookResponse.setPublisher("DPB");
        bookResponse.setPrice(100);
        bookList = new ArrayList<>();
        bookList.add(bookResponse);

        when(bookService.findById(book.getBook_id())).thenReturn(java.util.Optional.ofNullable(bookResponse));
        mockMvc.perform(get("/api/v1/get/book/1"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void givenBookToUpdateThenShouldReturnUpdatedBook() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        bookRequest = new BookRequest();
        bookRequest.setTitle("java");
        bookRequest.setPublisher("DPB");
        bookRequest.setPrice(100);

        bookList2 = new ArrayList<>();
        bookList2.add(bookRequest);
        when(bookService.updateBook(bookRequest.getBook_id(),bookRequest)).thenReturn(book);
        mockMvc.perform(put("/api/v1/1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(book)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
}
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
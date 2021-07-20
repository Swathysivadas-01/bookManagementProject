package com.example.BookManagement.controller;

import com.example.BookManagement.entity.Author;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.service.AuthorServiceDAO;
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
import org.springframework.beans.factory.annotation.Qualifier;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthorControllerTwoTest {
    private MockMvc mockMvc;
    @Mock
    @Qualifier("authorServiceTwo")
    AuthorServiceDAO authorService;
    @InjectMocks
    private AuthorControllerTwo authorController;

    private List<Author> authorList1;
    private AuthorResponse authorResponse;
    private Author author;
    private List<AuthorResponse> authorList;
    private AuthorRequest authorRequest;
    private List<AuthorRequest> authorList2;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();

        author = new Author();
        author.setAuthor_id(1);
        author.setName("swathy");
        author.setCountry("india");

        authorList1 = new ArrayList<>();
        authorList1.add(author);

    }

    @AfterEach
    public void tearDown() {
        author = null;
    }

    @Test
    public void givenAuthorToSaveThenReturnSavedAuthor() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
        authorRequest = new AuthorRequest();
        authorRequest.setAuthor_id(1);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");

        authorList2 = new ArrayList<>();
        authorList2.add(authorRequest);

        when(authorService.saveAuthor(any())).thenReturn(author);
        mockMvc.perform(post("/api/v1/second/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(author)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(authorService).saveAuthor(any());
    }

    @Test
    public void givenGetAllAuthorsThenShouldReturnListOfAllAuthors() throws Exception {
        when(authorService.findAllAuthor()).thenReturn(authorList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/second/authors")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(author)))
                .andDo(MockMvcResultHandlers.print());
        verify(authorService).findAllAuthor();
        verify(authorService, times(1)).findAllAuthor();

    }

    @Test
    void givenAuthorIdThenShouldReturnRespectiveAuthor() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
        authorResponse = new AuthorResponse();
        authorResponse.setAuthor_id(1);
        authorResponse.setName("swathy");
        authorResponse.setCountry("india");

        authorList = new ArrayList<>();
        authorList.add(authorResponse);

        when(authorService.findById(author.getAuthor_id())).thenReturn(java.util.Optional.ofNullable(authorResponse));
        mockMvc.perform(get("/api/v1/second/author/1"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenAuthorIdToDeleteThenShouldNotReturnDeletedAuthor() throws Exception {
        when(authorService.deleteAuthor(author.getAuthor_id())).thenReturn(author);
        mockMvc.perform(delete("/api/v1/1/second/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(author)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenAuthorToUpdateThenShouldReturnUpdatedAuthor() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
        authorRequest=new AuthorRequest();
        authorRequest.setAuthor_id(1);
        authorRequest.setName("swathy");
        authorRequest.setCountry("india");

        authorList2 = new ArrayList<>();
        authorList2.add(authorRequest);
        when(authorService.updateAuthor(authorRequest.getAuthor_id(),authorRequest)).thenReturn(author);
        mockMvc.perform(put("/api/v1/1/second/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(author)))
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
package com.example.BookManagement.model.request;

import com.example.BookManagement.entity.Book;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AuthorRequest {

    private int author_id;
    private String name;
    private String country;
    private List<Book> book;
}

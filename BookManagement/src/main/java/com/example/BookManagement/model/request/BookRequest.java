package com.example.BookManagement.model.request;

import com.example.BookManagement.entity.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private int book_id;
    private String title;
    private String publisher;
    private int price;
    private Author author;
}

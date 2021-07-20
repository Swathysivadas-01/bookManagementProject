package com.example.BookManagement.model.response;

import com.example.BookManagement.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorResponse implements Comparable<AuthorResponse>
{
    private int author_id;
    private String name;
    private String country;
    private List<Book> book;

    @Override
    public int compareTo(AuthorResponse o) {
        return name.compareTo(o.name);
    }

}

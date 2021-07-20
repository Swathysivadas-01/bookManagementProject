package com.example.BookManagement.entity;

import com.example.BookManagement.model.response.AuthorResponse;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "author_id")
public class Author implements Comparable<Author>  {
    //@Id annotation makes author_id variable as Primary key
    @Id
    private int author_id;

    private String name;
    private String country;
    @OneToMany(mappedBy = "author")
    private List<Book> book;

    @Override
    public int compareTo(Author a) {
        return name.compareTo(a.name);


    }
}

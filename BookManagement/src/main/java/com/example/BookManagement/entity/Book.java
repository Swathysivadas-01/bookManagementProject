package com.example.BookManagement.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "book_id")
public class Book {
    //@Id annotation makes book_id variable as Primary key
    @Id
    private int book_id;
    private String title;
    private String publisher;
    private int price;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}

package com.example.BookManagement.repository;

import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Integer> {
    @Query("select book from Author a where a.author_id = ?1")
    List<Book> getBooksByAuthor(int id);
}

package com.example.BookManagement.repository;

import com.example.BookManagement.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {

}

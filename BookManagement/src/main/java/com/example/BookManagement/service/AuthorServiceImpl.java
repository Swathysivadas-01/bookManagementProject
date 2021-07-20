package com.example.BookManagement.service;

import com.example.BookManagement.exception.FieldCannotBeEmptyException;
import com.example.BookManagement.exception.IdAlreadyExistsException;
import com.example.BookManagement.exception.IdNotFoundException;
import com.example.BookManagement.entity.Author;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.model.request.AuthorRequest;
import com.example.BookManagement.model.response.AuthorResponse;
import com.example.BookManagement.model.response.BookResponse;
import com.example.BookManagement.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Qualifier("authorServiceOne")
public class AuthorServiceImpl implements AuthorServiceDAO {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    ModelMapper mapper;

    //Implementation of findAllAuthor method
    @Override
    public List<AuthorResponse> findAllAuthor() {
        List<AuthorResponse> response = new ArrayList<>();
        Iterable<Author> all = authorRepository.findAll();
        if (all!=null){
            response = mapper.map(all,List.class);
        }
        return response;

    }


    //Implementation of findById method
    @Override
    public Optional<AuthorResponse> findById(int id) {
        Optional<AuthorResponse> response = mapper.map(authorRepository.findById(id), Optional.class);
        return response;
    }


    //Implementation of getBooksByAuthor method
    @Override
    public List<Book> getBooksByAuthor(int id) {
        List<Book> response = mapper.map(authorRepository.getBooksByAuthor(id),List.class);
        return response;
    }


    //Implementation of saveAuthor method
    @Override
    public Author saveAuthor(AuthorRequest authorRequest) throws IdAlreadyExistsException,FieldCannotBeEmptyException {
        if (authorRequest.getName() == null || authorRequest.getName() == ""||authorRequest.getCountry() == null||authorRequest.getCountry()==""||authorRequest.getAuthor_id()<=0) {
            throw new FieldCannotBeEmptyException();
        }
         else
             if (authorRepository.existsById(authorRequest.getAuthor_id())){
                throw new IdAlreadyExistsException();
            }

        Author author = mapper.map(authorRequest,Author.class);
        return authorRepository.save(author);
    }

    //Implementation of updateAuthor method
    @Override
    public Author updateAuthor(int id ,AuthorRequest authorRequest) throws IdNotFoundException {
        if (!authorRepository.existsById(authorRequest.getAuthor_id())){
            throw new IdNotFoundException();
        }
        Author author = mapper.map(authorRequest,Author.class);
        return authorRepository.save(author);
    }


    //Implementation of deleteAuthor method
    @Override
    public Author deleteAuthor(int id) throws IdNotFoundException {
        if (!authorRepository.existsById(id)){
            throw new IdNotFoundException();
        }
        Author author =  authorRepository.findById(id).get();
        authorRepository.deleteById(id);
       return author ;
    }

    @Override
    public int findAuthorCount() {
        List<AuthorResponse> authorResponses = mapper.map(authorRepository.findAll(),List.class);
        return authorResponses.size();
    }

    @Override
    public int findBooksCountByAuthor(int id) {
        List<Book> response = mapper.map(authorRepository.getBooksByAuthor(id),List.class);
        return response.size();
    }

    @Override
    public List<AuthorResponse> findSortedAuthorList() {
        List<AuthorResponse> authorResponses = mapper.map(authorRepository.findAll(),List.class);
        Collections.sort(authorResponses);
        return authorResponses;
    }

    @Override
    public List<AuthorResponse> findReversedSortedAuthorList() {
        List<AuthorResponse> authorResponses = mapper.map(authorRepository.findAll(),List.class);
        Collections.sort(authorResponses,Collections.reverseOrder());
        return authorResponses;
    }

    @Override
    public List<AuthorResponse> findAuthorByCountry(String country) {
        List<Author> authorResponses = (List<Author>) authorRepository.findAll();
        List<Author> result = authorResponses.stream()
                .filter(authorResponse -> authorResponse.getCountry().matches(country))
                .collect(Collectors.toList());
        List<AuthorResponse> authorResponse = mapper.map(result,List.class);
        return authorResponse;
    }

}

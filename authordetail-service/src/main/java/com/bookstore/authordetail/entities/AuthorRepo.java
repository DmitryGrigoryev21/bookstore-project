package com.bookstore.authordetail.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Integer>{
    public Author findAuthorByAuthor (String author);
    public boolean existsAuthorByAuthor(String author);
    public void deleteAuthorByAuthor(String author);
}

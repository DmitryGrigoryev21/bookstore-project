package com.grigoryev.bookstore.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {
    public Book findBookByBookUUID(String bookUUID);
    public boolean existsBookByBookUUID(String bookUUID);
    public void deleteBookByBookUUID(String bookUUID);
}

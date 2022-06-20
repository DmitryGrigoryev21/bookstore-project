package com.bookstore.bookreview.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends CrudRepository<Review, Integer> {
    public List<Review> findAllByBookUUID(String bookUUID);
    public Review findByBookUUID(String bookUUID);
    public boolean existsByBookUUID(String bookUUID);
    public void deleteByBookUUID(String bookUUID);
}

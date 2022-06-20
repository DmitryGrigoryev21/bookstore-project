package com.bookstore.bookrecommendation.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepo extends CrudRepository<Tag, Integer> {
    public List<Tag> findAllByBookUUID(String bookUUID);
    public Tag findByBookUUID(String bookUUID);
    public List<Tag> findAllByTag(String tag);
    public Tag findByTag(String tag);
    public boolean existsByBookUUID(String bookUUID);
    public void deleteByBookUUID(String bookUUID);
}

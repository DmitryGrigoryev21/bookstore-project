package com.bookstore.bookrecommendation.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TagRepoTest {

    @Autowired
    private TagRepo repo;

    private final String valid_bookUUID = "123";
    private final String valid_tag = "tag";
    private final String invalid_bookUUID = "999";
    private final String update_tag = "tagged";

    private Tag entity;

    @BeforeEach
    void setUp() {
        repo.deleteAll();

        Tag newTag = new Tag();

        newTag.setBookUUID(valid_bookUUID);
        newTag.setTag(valid_tag);

        entity = repo.save(newTag);
    }

    @Test
    void findTagsWithMatchingId() {
        Tag tag = repo.findByBookUUID(valid_bookUUID);

        assertThat(tag, samePropertyValuesAs(entity));
    }

    @Test
    void findTagWithMatchingTag() {
        Tag tag = repo.findByTag(valid_tag);

        assertThat(tag, samePropertyValuesAs(entity));
    }
    @Test
    void bookUUIDExists() {
        assertTrue(repo.existsByBookUUID(valid_bookUUID));
    }

    @Test
    void bookUUIDDoesNotExist(){
        assertFalse(repo.existsByBookUUID(invalid_bookUUID));
    }

    @Test
    @Transactional
    void deleteByBookUUID() {
        repo.deleteByBookUUID(valid_bookUUID);
        assertFalse(repo.existsByBookUUID(valid_bookUUID));
    }

    @Test
    void updatingTagWithValidBookUUID(){

        entity.setTag(update_tag);

        assertEquals(update_tag, entity.getTag());
    }

    @Test
    void createNewTagWithValidBookUUID(){
        Tag tag = new Tag();

        tag.setBookUUID("234");
        tag.setTag("taggage");

        repo.save(tag);

        assertEquals(2, repo.count());
        assertTrue(repo.existsByBookUUID(tag.getBookUUID()));
    }
}
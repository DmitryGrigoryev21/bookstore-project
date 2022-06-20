package com.bookstore.authordetail.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthorRepoTest {
//
//    @Autowired
//    private AuthorRepo repo;
//
//    private final String valid_author = "me";
//    private final String valid_bestseller = "book";
//    private final String valid_bio = "goodguy";
//    private final String invalid_author = "you";
//    private final String update_bio = "badguy";
//
//    private Author entity;
//
//    @BeforeEach
//    void setUp() {
//        repo.deleteAll();
//
//        Author newAuthor = new Author();
//
//        newAuthor.setAuthor(valid_author);
//        newAuthor.setBestseller(valid_bestseller);
//        newAuthor.setBio(valid_bio);
//
//        entity = repo.save(newAuthor);
//    }
//    @Test
//    void findAuthorWithMatchingAuthor() {
//        Author author = repo.findAuthorByAuthor(valid_author);
//
//        assertThat(author, samePropertyValuesAs(entity));
//    }
//
//    @Test
//    void AuthorExists() {
//        assertTrue(repo.existsAuthorByAuthor(valid_author));
//    }
//
//    @Test
//    void AuthorDoesNotExist(){
//        assertFalse(repo.existsAuthorByAuthor(invalid_author));
//    }
//
//    @Test
//    @Transactional
//    @Disabled
//    void deleteByAuthor() {
//        repo.deleteAuthorByAuthor(valid_author);
//        assertFalse(repo.existsAuthorByAuthor(valid_author));
//    }
//
//    @Test
//    void updatingAuthorWithValidAuthor(){
//
//        entity.setBio(update_bio);
//
//        assertEquals(update_bio, entity.getBio());
//    }
//
//    @Test
//    void createNewAuthorWithValidAuthor(){
//        Author author = new Author();
//
//        author.setAuthor("someone");
//        author.setBestseller("somebook");
//        author.setBio("somebio");
//
//        repo.save(author);
//
//        assertEquals(2, repo.count());
//        assertTrue(repo.existsAuthorByAuthor(author.getAuthor()));
//    }
}
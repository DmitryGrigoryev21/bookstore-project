package com.grigoryev.bookstore.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookRepoTest {

    @Autowired
    private BookRepo repo;

    private final String valid_bookUUID = "123";
    private final String valid_title = "title";
    private final String valid_author = "author";
    private final String valid_summary = "summary";
    private final String valid_isbn = "isbn";
    private final double valid_price = 10.00;
    private final String invalid_bookUUID = "999";
    private final String update_title = "newTitle";

    private Book entity;

    @BeforeEach
    void setUp() {
        repo.deleteAll();

        Book newBook = new Book();

        newBook.setBookUUID(valid_bookUUID);
        newBook.setTitle(valid_title);
        newBook.setAuthor(valid_author);
        newBook.setSummary(valid_summary);
        newBook.setIsbn(valid_isbn);
        newBook.setPrice(valid_price);

        entity = repo.save(newBook);
    }

    @Test
    void findBookWithMatchingId() {
        Book tag = repo.findBookByBookUUID(valid_bookUUID);

        assertThat(tag, samePropertyValuesAs(entity));
    }

    @Test
    void bookUUIDExists() {
        assertTrue(repo.existsBookByBookUUID(valid_bookUUID));
    }

    @Test
    void bookUUIDDoesNotExist(){
        assertFalse(repo.existsBookByBookUUID(invalid_bookUUID));
    }

    @Test
    @Transactional
    void deleteByBookUUID() {
        repo.deleteBookByBookUUID(valid_bookUUID);
        assertFalse(repo.existsBookByBookUUID(valid_bookUUID));
    }

    @Test
    void updatingBookWithValidBookUUID(){

        entity.setTitle(update_title);

        assertEquals(update_title, entity.getTitle());
    }

    @Test
    void createNewTagWithValidBookUUID(){
        Book book = new Book();

        book.setBookUUID("234");
        book.setTitle("title2");
        book.setAuthor("author2");
        book.setSummary("summary2");
        book.setIsbn("isbn2");
        book.setPrice(30.00);

        repo.save(book);

        assertEquals(2, repo.count());
        assertTrue(repo.existsBookByBookUUID(book.getBookUUID()));
    }
}
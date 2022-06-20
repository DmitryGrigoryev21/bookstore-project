package com.grigoryev.bookstore.controllers;

import com.grigoryev.bookstore.entities.Book;
import com.grigoryev.bookstore.entities.BookRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import static reactor.core.publisher.Mono.just;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"spring.datasource.url=jdbc:h2:mem:bookstore-db"})
@Sql({"/schema-h2.sql", "/data-h2.sql"})
@AutoConfigureWebTestClient
class BookControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private BookRepo repo;

    private static String url = "/api";

    private static final String valid_bookUUID = "e74064af-16db-43b5-ad9a-f809ebd56f98";

    @AfterEach
    void setupDb(){

        repo.deleteAll();

    }

    @Test
    void getAllBooks() {

        int expectedLength = 1;

        List<Book> books = (List<Book>) repo.findAll();

        assertEquals(expectedLength, books.size());

        client.get()
                .uri(url + "/books/detailed")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(expectedLength)
                .jsonPath("$[0].bookUUID").isEqualTo(valid_bookUUID);
    }
    @Test
    void getOneBook() {
        client.get()
                .uri("/api/book/" + valid_bookUUID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.bookUUID").isEqualTo(valid_bookUUID);
    }

    @Test
    void insertBook() {

        int expectedSize = 2;

        Book newBook = new Book();

        newBook.setBookUUID("4353");
        newBook.setTitle("45");
        newBook.setAuthor("435");
        newBook.setSummary("4353");
        newBook.setIsbn("345");
        newBook.setPrice(45.67);

        client.post()
                .uri(url + "/book")
                .body(just(newBook), Book.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody();

        assertEquals(expectedSize, repo.count());

    }
    @Test
    void deleteBook() {


        client.delete()
                .uri(url + "/book/" + valid_bookUUID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();

        assertEquals(0, repo.count());
    }
}
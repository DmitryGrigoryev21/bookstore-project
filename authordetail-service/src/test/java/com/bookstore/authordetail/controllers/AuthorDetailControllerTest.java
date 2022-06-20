package com.bookstore.authordetail.controllers;


import com.bookstore.authordetail.AuthorPopulatorConfiguration;
import com.bookstore.authordetail.entities.Author;
import com.bookstore.authordetail.entities.AuthorRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import static reactor.core.publisher.Mono.just;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(AuthorPopulatorConfiguration.class)
class AuthorDetailControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private AuthorRepo repo;

    private static String url = "/api";

    private static final String valid_author = "Leo Tolstoy";

    @BeforeEach
    void tearDown() {
        repo.deleteAll();

        Author author = new Author();
        author.setAuthor("Leo Tolstoy");
        author.setBestseller("yway");
        author.setBio("wdwe");

        repo.save(author);
    }

    @Test
    void getAllBooks() {

        int expectedLength = 1;

        List<Author> authors = (List<Author>) repo.findAll();

        assertEquals(expectedLength, authors.size());

        client.get()
                .uri(url + "/authors")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(expectedLength)
                .jsonPath("$[0].author").isEqualTo(valid_author);
    }
    @Test
    void getOneBook() {
        client.get()
                .uri(url + "/author/" + valid_author)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.author").isEqualTo(valid_author);
    }

    @Test
    void insertBook() {

        int expectedSize = 2;

        Author newAuthor = new Author();

        newAuthor.setAuthor("4353");
        newAuthor.setBestseller("45");
        newAuthor.setBio("435");

        client.post()
                .uri(url + "/author")
                .body(just(newAuthor), Author.class)
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
                .uri(url + "/author/" + valid_author)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();

        assertEquals(0, repo.count());
    }
}
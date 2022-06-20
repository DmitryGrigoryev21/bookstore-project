package com.bookstore.bookreview.controllers;

import com.bookstore.bookreview.entities.Review;
import com.bookstore.bookreview.entities.ReviewRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.just;

import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"spring.datasource.url=jdbc:h2:mem:bookreview-db"})
@Sql({"/schema-h2.sql", "/data-h2.sql"})
@AutoConfigureWebTestClient
class ReviewControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private ReviewRepo repo;

    private static String url = "/api";

    private static final String valid_bookUUID = "e74064af-16db-43b5-ad9a-f809ebd56f98";

    @AfterEach
    void setupDb(){

        repo.deleteAll();

    }

    @Test
    void getAllReviews() {

        int expectedLength = 2;

        List<Review> reviews = (List<Review>) repo.findAll();

        assertEquals(expectedLength, reviews.size());

        client.get()
                .uri(url + "/reviews")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(expectedLength)
                .jsonPath("$[0].bookUUID").isEqualTo(valid_bookUUID);
    }
    @Test
    void getOneReview() {
        client.get()
                .uri("/api/reviews/" + valid_bookUUID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].bookUUID").isEqualTo(valid_bookUUID);
    }

}

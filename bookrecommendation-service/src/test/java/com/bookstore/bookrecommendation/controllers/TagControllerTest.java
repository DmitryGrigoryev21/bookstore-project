package com.bookstore.bookrecommendation.controllers;

import com.bookstore.bookrecommendation.entities.Tag;
import com.bookstore.bookrecommendation.entities.TagRepo;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"spring.datasource.url=jdbc:h2:mem:bookrecommendation-db"})
@Sql({"/schema-h2.sql", "/data-h2.sql"})
@AutoConfigureWebTestClient
class TagControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private TagRepo repo;

    private static String url = "/api";

    private static final String valid_bookUUID = "e74064af-16db-43b5-ad9a-f809ebd56f98";

    @AfterEach
    void setupDb(){

        repo.deleteAll();

    }

    @Test
    void getAllTags() {

        int expectedLength = 3;

        List<Tag> reviews = (List<Tag>) repo.findAll();

        assertEquals(expectedLength, reviews.size());

        client.get()
                .uri(url + "/tags")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(expectedLength)
                .jsonPath("$[0].bookUUID").isEqualTo(valid_bookUUID);
    }
    @Test
    void getOneTag() {
        client.get()
                .uri("/api/tags/" + valid_bookUUID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].bookUUID").isEqualTo(valid_bookUUID);
    }
}
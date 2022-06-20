package com.bookstore.bookreview.entities;

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
class ReviewRepoTest {

    @Autowired
    private ReviewRepo repo;

    //private final int valid_id = 1;
    private final String valid_bookUUID = "123";
    private final String valid_review = "review";
    private final int valid_rating = 5;
    private final String invalid_bookUUID = "999";
    private final float update_rating = 1;

    private Review entity;

    @BeforeEach
    void setUp() {
            repo.deleteAll();

            Review newReview = new Review();

            newReview.setBookUUID(valid_bookUUID);
            newReview.setReview(valid_review);
            newReview.setRating(valid_rating);

            entity = repo.save(newReview);
    }

    @Test
    void findReviewsWithMatchingId() {

        Review review = repo.findByBookUUID(valid_bookUUID);

        assertThat(review, samePropertyValuesAs(entity));
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
    @Disabled
    void deleteByBookUUID() {
        repo.deleteByBookUUID(valid_bookUUID);
        assertFalse(repo.existsByBookUUID(valid_bookUUID));
    }

    @Test
    void updatingReviewWithValidBookUUID(){

        entity.setRating((int) update_rating);

        assertEquals(update_rating, entity.getRating());
    }

    @Test
    void createNewReviewWithValidBookUUID(){
        Review review = new Review();

        review.setBookUUID("234");
        review.setReview("reviewed");
        review.setRating(3);

        repo.save(review);

        assertEquals(2, repo.count());
        assertTrue(repo.existsByBookUUID(review.getBookUUID()));
    }

}
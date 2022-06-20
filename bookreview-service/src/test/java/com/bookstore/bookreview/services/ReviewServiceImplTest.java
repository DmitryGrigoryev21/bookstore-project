package com.bookstore.bookreview.services;

import com.bookstore.bookreview.controllers.ReviewDTO;
import com.bookstore.bookreview.entities.Review;
import com.bookstore.bookreview.entities.ReviewRepo;
import com.bookstore.bookreview.mappers.ReviewMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class ReviewServiceImplTest {

    @Autowired
    ReviewService reviewService;

    @MockBean
    ReviewRepo repo;

    @SpyBean
    ReviewMapper mapper;

    @AfterEach
    void tearDown() {

        repo.deleteAll();
    }

    @Test
    void findAll() {

        List<ReviewDTO> reviewDTOS;

        reviewDTOS = reviewService.findAllReviews();

        assertNotNull(reviewDTOS);
    }

    @Test
    void findReview() {

        Review review = new Review();
        ReviewDTO model;
        review.setBookUUID("3425");
        review.setReview("dsge");
        review.setRating(4);

        repo.save(review);

        when(repo.save(any(Review.class))).thenAnswer(i -> i.getArguments()[0]);
        when(repo.existsByBookUUID(review.getBookUUID())).thenReturn(true);
        when(repo.findByBookUUID(review.getBookUUID())).thenReturn(review);

        model = reviewService.findByUUID(review.getBookUUID());

        assertNotNull(model);
    }
    @Test
    void newReview() {

        ReviewDTO reviewDTO = new ReviewDTO();
        ReviewDTO model;

        reviewDTO.setBookUUID("352");
        reviewDTO.setReview("gergherh");
        reviewDTO.setRating(3);

        model = reviewService.saveReview(reviewDTO);

        assertNotNull(model);


    }
}
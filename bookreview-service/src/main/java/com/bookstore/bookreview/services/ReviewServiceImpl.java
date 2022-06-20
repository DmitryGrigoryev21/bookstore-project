package com.bookstore.bookreview.services;


import com.bookstore.bookreview.controllers.ReviewDTO;
import com.bookstore.bookreview.entities.Review;
import com.bookstore.bookreview.entities.ReviewRepo;
import com.bookstore.bookreview.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepo reviewRepo;

    @Autowired
    private ReviewMapper reviewMapper;

    ReviewServiceImpl(ReviewRepo reviewRepo){
        this.reviewRepo=reviewRepo;
    }

    @Override
    public List<ReviewDTO> findAllReviews() {

        List<Review> reviews = (List<Review>) reviewRepo.findAll();
        List<ReviewDTO> reviewDTOS = reviewMapper.entityListToResponseModelList(reviews);
        return reviewDTOS;
    }

    @Override
    public ReviewDTO findByUUID(String Id) {
        Review review = reviewRepo.findByBookUUID(Id);
        return reviewMapper.entityToModel(review);
    }

    @Override
    public List<ReviewDTO> findByBookUUID(String Id) {
        List<Review> review = (List<Review>) reviewRepo.findAllByBookUUID(Id);
        return reviewMapper.entityListToResponseModelList(review);
    }

    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.modelToEntity(reviewDTO);
        reviewRepo.save(review);
        return reviewMapper.entityToModel(review);
    }

    @Override
    public Double averageReview(String Id){
        List<Review> reviews = (List<Review>) reviewRepo.findAllByBookUUID(Id);
        int total=0;
        for(Review x: reviews){
            total+= x.getRating();
        }
        return (double)total/reviews.size();
    }

    @Transactional
    @Override
    public boolean deleteReviewByBookUUID(String Id) {
        if (reviewRepo.existsByBookUUID(Id)) {
            reviewRepo.deleteByBookUUID(Id);
            return true;
        }
        else{
            throw new EntityNotFoundException("Invalid bookUUID was provided.");
        }
    }

    @Override
    public ReviewDTO updateReviewByBookUUID(String Id, ReviewDTO reviewDTO) {
        if (reviewRepo.existsByBookUUID(Id)) {
            Review review = reviewRepo.findByBookUUID(Id);
            Review reviewTemp = reviewMapper.modelToEntity(reviewDTO);
            review.setReview(reviewTemp.getReview());
            review.setRating(reviewTemp.getRating());
            reviewRepo.save(review);
            return reviewMapper.entityToModel(review);
        }
        else{
            throw new EntityNotFoundException("Invalid bookUUID was provided.");
        }
    }
}

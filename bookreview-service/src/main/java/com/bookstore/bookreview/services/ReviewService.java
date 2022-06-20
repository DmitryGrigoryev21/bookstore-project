package com.bookstore.bookreview.services;



import com.bookstore.bookreview.controllers.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public List<ReviewDTO> findAllReviews();
    public ReviewDTO findByUUID(String Id);
    public List<ReviewDTO> findByBookUUID(String Id);
    public ReviewDTO saveReview(ReviewDTO reviewDTO);
    public Double averageReview(String Id);
    public boolean deleteReviewByBookUUID(String Id);
    public ReviewDTO updateReviewByBookUUID(String Id, ReviewDTO reviewDTO);
}

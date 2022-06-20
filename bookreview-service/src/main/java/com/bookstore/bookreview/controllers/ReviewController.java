package com.bookstore.bookreview.controllers;

import com.bookstore.bookreview.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

//    @GetMapping("/review/{Id}")
//    public ReviewDTO listReviewByID(@PathVariable Integer Id){
//        return reviewService.findByID(Id);
//    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> listReviews(){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.findAllReviews());
    }

    @GetMapping("/reviews/{Id}")
    public ResponseEntity<List<ReviewDTO>> listReviewsByBookUUID(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.findByBookUUID(Id));
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewDTO> newReview(@RequestBody ReviewDTO reviewDTO)
    {
        try {
            ReviewDTO newReview = reviewService.saveReview(reviewDTO);
            return ResponseEntity.status(HttpStatus.OK).body(newReview);
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/review/{Id}/average")
    public ResponseEntity<Double> listAverageRating(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.averageReview(Id));
    }

    @DeleteMapping("/review/{Id}")
    public ResponseEntity<String> deleteReviewByBookUUID(@PathVariable String Id) {
        boolean success = reviewService.deleteReviewByBookUUID(Id);
        String response;
        if (success)
            response = "Success";
        else
            response = "Aborted";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/review/{Id}")
    public ResponseEntity<ReviewDTO> updateReviewByBookUUID(@RequestBody ReviewDTO reviewDTO, @PathVariable String Id) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReviewByBookUUID(Id,reviewDTO));
    }
}

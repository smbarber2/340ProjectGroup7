package com._Project.Tbay.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review getReviewById(long reviewId) {return reviewRepository.findById(reviewId).orElse(null);}

    public List<Review> getAllReviews() { return reviewRepository.findAll();}

    public void deleteReviewById(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public void addNewReview (Review review){reviewRepository.save(review);}

}

package com._Project.Tbay.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public void deleteReviewById(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}

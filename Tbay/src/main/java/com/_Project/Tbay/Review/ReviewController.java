package com._Project.Tbay.Review;

import com._Project.Tbay.Listing.ListingService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")

public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    private ListingService listingService;



    @DeleteMapping("/delete/{reviewId}")
    public List<Review> deleteReviewById(@PathVariable long reviewId) {
        reviewService.deleteReviewById(reviewId);
        return null;
    }
}

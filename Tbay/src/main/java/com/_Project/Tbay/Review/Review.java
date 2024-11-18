package com._Project.Tbay.Review;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long reviewId;

    private long listingId;

    private long userId;

    private String description;

    public Review(long reviewId, long listingId, long userId, String description) {
        this.reviewId = reviewId;
        this.listingId =listingId;
        this.userId = userId;
        this.description = description;
    }

    public Review() {}

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public long getListingId() {
        return listingId;
    }

    public void setUserId(long userId) { this.userId = userId; }

    public long getUserId() { return userId; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

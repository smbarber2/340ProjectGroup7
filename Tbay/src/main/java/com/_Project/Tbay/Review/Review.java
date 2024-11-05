package com._Project.Tbay.Review;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long reviewId;

    private String description;

    public String getDescription() {
        return description;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

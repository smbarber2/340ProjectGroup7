package com._Project.Tbay.Listing;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long listingId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long sellerId;

    private String tag;

    private String description;

    @Column(nullable = false)
    private float price;

    private List<Long> reviewIds;

    public Listing(long listingId, String name, String tag, String description, float price, List<Long> reviewIds, long sellerId) {
        this.listingId = listingId;
        this.name = name;
        this.tag = tag;
        this.description = description;
        this.price = price;
        this.reviewIds = reviewIds;
        this.sellerId = sellerId;
    }
    public Listing(){}

    public Listing(long listingId, String name, float price) {
        this.listingId = listingId;
        this.name = name;
        this.price = price;
    }


    public void setListingId(long listingId){
        this.listingId = listingId;
    }

    public long getListingId(){
        return listingId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setReviewIds(List<Long> reviewIds) {
        this.reviewIds = reviewIds;
    }

    public List<Long> getReviewIds() {
        return reviewIds;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }
}

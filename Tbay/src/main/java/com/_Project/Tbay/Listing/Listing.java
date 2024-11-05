package com._Project.Tbay.Listing;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int listingId;

    @Column(nullable = false)
    private String name;

    private List<String> tag;

    private String description;

    @Column(nullable = false)
    private float price;

    public Listing(int listingId, String name, List<String> tag, String description, float price) {
        this.listingId = listingId;
        this.name = name;
        this.tag = tag;
        this.description = description;
        this.price = price;
    }

    public void setListingId(int listingId){
        this.listingId = listingId;
    }

    public int getListingId(){
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

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}

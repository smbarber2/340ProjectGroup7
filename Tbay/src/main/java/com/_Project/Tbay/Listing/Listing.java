package com._Project.Tbay.Listing;

import jakarta.persistence.*;


@Entity
@Table(name = "listing")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int listingId;

    @Column(nullable = false)
    private String name;

    private String tag;

    private String description;

    @Column(nullable=false)
    protected boolean status;

    @Column(nullable = false)
    private float price;



}

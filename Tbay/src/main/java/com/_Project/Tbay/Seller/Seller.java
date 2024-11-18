package com._Project.Tbay.Seller;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="sellers")

public class Seller {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long sellerId;

    @Column(nullable=false)
    public String name;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private Date creationDate;

    @Column(nullable=false)
    protected boolean status;

    @Column(nullable=true)
    protected List<Integer> sellerListings;

    @Column(nullable=true)
    protected List<Integer> incomingOrders;

    @Column(nullable=true)
    protected List<Integer> completedOrders;

    @Column(nullable=true)
    public String bio;

    @Column(nullable=true)
    public String pfp;

    public Seller(long sellerId, String name, String password, String email, boolean status, String pfp, Date creationDate) {
        this.sellerId = sellerId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.status = status;
        this.creationDate = creationDate;
        this.pfp = pfp;
    }

    public Seller(long sellerId, String name, String password, String email) {
        this.sellerId = sellerId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Seller(){}

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Integer> getSellerListings() {
        return sellerListings;
    }

    public void setSellerListings(List<Integer> sellerListings){
        this.sellerListings = sellerListings;
    }

    public List<Integer> getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(List<Integer> completedOrders) {
        this.completedOrders = completedOrders;
    }

    public List<Integer> getIncomingOrders() {
        return incomingOrders;
    }

    public void setIncomingOrders(List<Integer> incomingOrders) {
        this.incomingOrders = incomingOrders;
    }
}

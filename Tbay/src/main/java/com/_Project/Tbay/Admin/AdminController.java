package com._Project.Tbay.Admin;


import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private AdminService adminservice;
    private ListingService listingService;

    //GET all users
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return adminservice.getAllUsers();
    }

    //GET specific user
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable int userId){return adminservice.getUserById(userId);}

    //GET all listings
    @GetMapping("/allListings")
    public List<Listing> getAllListings(){
        return listingService.getAllListings();
    }

    //DELETE existing User
    @DeleteMapping("/delete/{userId}")
    public List<User> deleteUserById(@PathVariable int userId) {
        adminservice.deleteUserById(userId);
        return adminservice.getAllUsers();
    }
    //DELETE existing Listing
    @DeleteMapping("/delete/{listingId}")
    public List<Listing> deleteListingById(@PathVariable int listingId) {
        listingService.deleteListingById(listingId);
        return listingService.getAllListings();
    }
    //PUT review
    //@PutMapping("/")

    //DELETE existing Review
    //@DeleteMapping("/")



}
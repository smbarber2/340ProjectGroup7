package com._Project.Tbay.Admin;


import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private AdminService service;

    //GET all users
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    //GET specific user
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable int userId){return service.getUserById(userId);}

    //GET all listings
    @GetMapping("/allListings")
    public List<Listing> getAllListings(){
        return service.getAllListings();
    }

    //DELETE existing User
    @DeleteMapping("/delete/{userId}")
    public List<User> deleteUserById(@PathVariable int userId) {
        service.deleteUserById(userId);
        return service.getAllUsers();
    }
    //DELETE existing Listing
    @DeleteMapping("/delete/{listingId}")
    public List<Listing> deleteListingById(@PathVariable int listingId) {
        service.deleteListingById(listingId);
        return service.getAllListings();
    }
    //PUT review
    //@PutMapping("/")

    //DELETE existing Review
    //@DeleteMapping("/")



}
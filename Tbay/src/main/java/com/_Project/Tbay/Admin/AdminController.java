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
    private AdminService adminservice;
    private ListingService listingService;

    //GET all users
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return adminservice.getAllUsers();
    }

    //GET specific user
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable long userId){return adminservice.getUserById(userId);}

    //GET all listings - IN Listing controller
    //@GetMapping("/allListing")
    //public List<Listing> getAllListings(){
    //    return service.getAllListings();
    //}

    //DELETE existing User
    @DeleteMapping("/delete/{userId}")
    public List<User> deleteUserById(@PathVariable long userId) {
        adminservice.deleteUserById(userId);
        return adminservice.getAllUsers();
    }
}
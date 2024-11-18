package com._Project.Tbay.Admin;


import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private AdminService adminservice;
    private ListingService listingService;

    //GET all users
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", adminservice.getAllUsers());
        model.addAttribute("title", "All Users");
        return "user-list"; //Like the table from hw
    }


    //GET specific user
    @GetMapping("/{userId}")
    public String getUserById(@PathVariable long userId, Model model) {
        model.addAttribute("user", adminservice.getUserById(userId));
        model.addAttribute("title", userId);
        return "profile.html";
    }

    //GET all listings - IN Listing controller
    //@GetMapping("/allListing")
    //public List<Listing> getAllListings(){
    //    return service.getAllListings();
    //}

    //DELETE existing User
    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable long userId) {
        adminservice.deleteUserById(userId);
        return "redirect:/admin/all";
    }
}
package com._Project.Tbay.Admin;


import com._Project.Tbay.Comments.CommentService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.Seller.Seller;
import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private AdminService adminservice;
    @Autowired
    private ListingService listingService;
    @Autowired
    private CommentService commentService;


    @PostMapping("/new")
    public String addNewSeller(Admin admin){
        adminservice.addNewAdmin(admin);
        return "redirect:/admin/homepage/"+ admin.getAdminId();
    }

    //GET all users
    @GetMapping("/all/{adminId}")
    public String getAllUsers(@PathVariable long adminId, Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
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

    //DELETE existing User
    @GetMapping("/delete/{adminId}/{userId}")
    public String deleteUserById(@PathVariable long userId, @PathVariable long adminId, Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        adminservice.deleteUserById(userId);
        return "redirect:/admin/all/{adminId}";
    }

    @GetMapping("/delete/comment/{adminId}/{commentId}")
    public String deleteCommentById(@PathVariable long commentId, @PathVariable long adminId, Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        commentService.deleteCommentById(commentId);
        return "redirect:/comment/all/{adminId}";
    }

//    @GetMapping("/ban/{userid}")
//    public String showBanForm(@PathVariable int userId, Model model){
//        model.addAttribute("user", adminservice.getUserById(userId));
//        return "edit-profile";
//    }
//    @PostMapping("/ban")
//    public String banUser(Model model) {
//        adminservice.banUser(model.getUserById(), model);
//        return "redirect:/admin/banlist";
//    }

    @GetMapping("/profile/{adminId}")
    public String profile(@PathVariable long adminId, Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        model.addAttribute("title", adminId);

        String base64 = null;
        if (adminservice.getAdminById(adminId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(adminservice.getAdminById(adminId).getPfp());
        }
        model.addAttribute("profilePic", base64);

        return "adminpage";
    }
}
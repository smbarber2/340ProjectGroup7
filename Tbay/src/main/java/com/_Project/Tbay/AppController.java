
package com._Project.Tbay;

import com._Project.Tbay.Admin.AdminService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Seller.SellerService;
import com._Project.Tbay.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private AdminService adminService;

    @GetMapping({"", "/", "/home", "app"})
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "log-in";
    }

    @GetMapping("/create-acct")
    public String createAcct() {
        return "create-acct";
    }

    @GetMapping("/user/homepage/{userId}")
    public String homepageUser(@PathVariable long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("title", userId);
        return "homepage";
    }

    @GetMapping("/seller/homepage/{sellerId}")
    public String homepageSeller(@PathVariable long sellerId, Model model) {
        model.addAttribute("user", sellerService.getSellerById(sellerId));
        model.addAttribute("title", sellerId);
        return "homepage";
    }
    /*
    @GetMapping("/admin/{adminId}")
    public String profileA(@PathVariable long sellerId, Model model) {
        Model seller = model.addAttribute("seller", sellerId);
        return "profile";
    }

    @GetMapping("/admin/homepage/{adminId}")
    public String homepageAdmin(@PathVariable long adminId, Model model) {
        model.addAttribute("user", adminService.getAdminById(adminId));
        model.addAttribute("title", adminId);
        return "homepage";
    }
    */
}




package com._Project.Tbay;
import com._Project.Tbay.User.UserService;
import com._Project.Tbay.Admin.AdminService;
import com._Project.Tbay.Seller.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

    @Autowired
    private UserService UserService;

    @Autowired
    private AdminService AdminService;

    @Autowired
    private SellerService SellerService;

    @GetMapping({"", "/", "/home", "app"})
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/create-acct")
    public String createAcct() {
        return "create-acct";
    }

    @GetMapping("user/{userId}")
    public String profile(@PathVariable long userId, Model model) {
        Model user = model.addAttribute("user", userId);
        return "profile";
    }

    @GetMapping("/user/homepage/{userId}")
    public String homepageUser(@PathVariable long userId, Model model) {
        model.addAttribute("user", UserService.getUserById(userId));
        model.addAttribute("title", userId);
        return "homepage";
    }

    @GetMapping("seller/{sellerId}")
    public String profileS(@PathVariable long sellerId, Model model) {
        Model seller = model.addAttribute("seller", sellerId);
        return "profile";
    }

    @GetMapping("seller/homepage/{sellerId}")
    public String homepageSeller(@PathVariable long sellerId, Model model) {
        model.addAttribute("user", UserService.getUserById(sellerId));
        model.addAttribute("title", sellerId);
        return "homepage";
    }

    @GetMapping("admin/{adminId}")
    public String profileA(@PathVariable long sellerId, Model model) {
        Model seller = model.addAttribute("seller", sellerId);
        return "profile";
    }

    @GetMapping("admin/homepage/{adminId}")
    public String homepageAdmin(@PathVariable long adminId, Model model) {
        model.addAttribute("user", UserService.getUserById(adminId));
        model.addAttribute("title", adminId);
        return "homepage";
    }
}



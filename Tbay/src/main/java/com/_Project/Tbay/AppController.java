
package com._Project.Tbay;

import com._Project.Tbay.User.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

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

    @GetMapping("/{userId}")
    public String profile(@PathVariable long userId, Model model) {
        Model user = model.addAttribute("user", userId);
        return "profile";
    }

    @GetMapping("/homepage/{userId}")
    public String homepageUser(@PathVariable long userId, Model model) {
        Model user = model.addAttribute("user", userId);
        return "homepage";
    }



}



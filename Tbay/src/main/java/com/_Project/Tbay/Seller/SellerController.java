package com._Project.Tbay.Seller;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seller")

public class SellerController {
    @Autowired
    private SellerService service;

    /*@PostMapping("/new")
    public void addNewSeller(@RequestBody Seller seller) {
        service.addNewSeller(seller);
    }*/

    @GetMapping("/{sellerId}")
    public String getUserById(@PathVariable long sellerId, Model model) {
        model.addAttribute("seller", service.getSellerById(sellerId));
        model.addAttribute("title", sellerId);
        return "sellerpage";
    }




}

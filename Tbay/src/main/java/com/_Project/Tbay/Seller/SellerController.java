package com._Project.Tbay.Seller;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seller")

public class SellerController {
    @Autowired
    private SellerService service;
    @Autowired
    private ListingService listingService;

    @GetMapping("/{sellerId}")
    public String getSellerById(@PathVariable long sellerId, Model model) {
        model.addAttribute("seller", service.getSellerById(sellerId));
        model.addAttribute("title", sellerId);
        model.addAttribute("listingList", service.getSellerList(sellerId, "listings"));

        return "sellerpage";
    }

    @GetMapping("/sellerListings/{sellerId}")
    public String getUserById(@PathVariable long sellerId, Model model) {
        model.addAttribute("seller", service.getSellerById(sellerId));
        model.addAttribute("title", sellerId);

        model.addAttribute("listingList", service.getSellerList(sellerId, "listings"));

        return "ListingPage";
    }




}

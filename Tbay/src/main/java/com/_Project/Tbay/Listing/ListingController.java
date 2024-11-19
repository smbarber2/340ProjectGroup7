package com._Project.Tbay.Listing;

import com._Project.Tbay.Seller.SellerService;
import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/Listing")

public class ListingController {
    @Autowired
    private ListingService service;
    @Autowired
    private SellerService sellerService;

    @GetMapping("/allListing")
    public String getAllListings(Model model){
        model.addAttribute("listingList", service.getAllListings());
        model.addAttribute("title", "All listing");
        return "ListingPage";
    }

    @GetMapping("/{listingId}")
    public String getListing(@PathVariable long listingId, Model model) {
        model.addAttribute("listing", service.getListingById(listingId));
        model.addAttribute("title", "Listing Details:"+listingId);
        return "indivListing";
    }

    @GetMapping("/showNew/{sellerId}")
    public String showNewListing(@PathVariable long sellerId, Model model){
        return "indivListingAdd";
    }

    @PostMapping("/newListing")
    public String addNewListing(@RequestParam("sellerId") long sellerId, Listing listing){
        service.addNewListing(listing);
        sellerService.addToSellerList(sellerId, listing.getListingId(), "listings");
        return "redirect:/seller/"+sellerId;
    }

    /*@GetMapping("/updateListing/{listingId}")
    public String showUpdate(@PathVariable long listingId, Model model) {
       model.addAttribute("listing", service.getListingById(listingId));
       return "listingUpdate";
    }

    @PostMapping("/update")
    public String updateListing(Listing listing) {
        service.addNewListing(listing);
        return "redirect:/Listing/" +listing.getListingId();
    }

    @DeleteMapping("/delete/{listingId}")
    public String deleteListingById(@PathVariable long listingId) {
        service.deleteListingById(listingId);
        return "redirect:/Listing/allListing";
    }

    @GetMapping("/search") // /search?contains= input
    public String getListingBySearch(@RequestParam(name = "contains", defaultValue = "unspecified") String name, Model model) {
        model.addAttribute("listingList", service.getListingBySearch(name));
        model.addAttribute("title", "Name:" + name);
        return "ListingPage";
    }*/


}

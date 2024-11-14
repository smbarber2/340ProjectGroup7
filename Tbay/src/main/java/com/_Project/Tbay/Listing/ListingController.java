package com._Project.Tbay.Listing;

import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/Listing")

public class ListingController {
    @Autowired
    private ListingService service;

    @GetMapping("/allListing")
    public List<Listing> getAllListings(){
        return service.getAllListings();
    }

    @GetMapping("/{listingId}")
    public Listing getListing(@PathVariable long listingId) {
        return service.getListingById(listingId);
    }

    @PostMapping("/newListing")
    public List<Listing> addNewListing(@RequestBody Listing listing){
        service.addNewListing(listing);
        return service.getAllListings();
    }

    @PutMapping("/updateListing/{listingId}")
    public Listing updateListing(@PathVariable long listingId, @RequestBody Listing listing) {
        service.updateListing(listingId, listing);
        return service.getListingById(listingId);
    }

    @DeleteMapping("/delete/{listingId}")
    public List<Listing> deleteListingById(@PathVariable long listingId) {
        service.deleteListingById(listingId);
        return service.getAllListings();
    }

    //Listing by search through name [WORKS OMG]
    @GetMapping("/search") // /search?contains= input
    public List<Listing> getListingBySearch(@RequestParam(name = "contains", defaultValue = "unspecified") String name) {
        return service.getListingBySearch(name);
    }


}

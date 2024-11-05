package com._Project.Tbay.Listing;

import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Listing")

public class ListingController {
    @Autowired
    private ListingService service;

    @GetMapping("/allListing")
    public List<Listing> getAllListings(){
        return service.getAllListings();
    }

    @PostMapping("/newListing")
    public List<Listing> addNewListing(@RequestBody Listing listing){service.addNewListing(listing); return service.getAllListings();}

    @PutMapping("/updateListing/{listingId}")
    public Listing updateListing(@PathVariable long listingId, @RequestBody Listing listing) {
        service.updateListing(listingId, listing);
        return service.getListingById(listingId);
    }



}

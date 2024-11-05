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

    @GetMapping("/all")
    public List<Listing> getAllListings(){
        return service.getAllListings();
    }
}

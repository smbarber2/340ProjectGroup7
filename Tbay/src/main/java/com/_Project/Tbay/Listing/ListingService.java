package com._Project.Tbay.Listing;

import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;

    /**
     Fetch all listings.*
     @return the list of all listings.*/
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }
}

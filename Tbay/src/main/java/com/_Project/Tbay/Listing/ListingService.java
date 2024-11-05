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

    public Listing getListingById(long listingId) {return listingRepository.findById(listingId).orElse(null);}

    public void addNewListing(Listing listing){listingRepository.save(listing);}

    public void updateListing(long listingId, Listing listing) {
        Listing existing = getListingById(listingId);
        existing.setName(listing.getName());
        existing.setTag(listing.getTag());
        existing.setDescription(listing.getDescription());
        existing.setPrice(listing.getPrice());

        listingRepository.save(existing);
    }

    public void deleteListingById(long listingId) {
        listingRepository.deleteById(listingId);
    }

}

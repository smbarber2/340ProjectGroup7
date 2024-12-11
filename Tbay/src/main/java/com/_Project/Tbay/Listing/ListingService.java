package com._Project.Tbay.Listing;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartService;
import com._Project.Tbay.Seller.SellerService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;
    @Lazy
    @Autowired
    private CartService cartService;
    @Lazy
    @Autowired
    private SellerService sellerService;

    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);


    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Listing getListingById(long listingId) {
        return listingRepository.findById(listingId).orElse(null);
    }

    public void addNewListing(Listing listing){
        try {
            if(listing.getPfp()==null){
                ClassPathResource resource = new ClassPathResource("static/tbaylogosquare.PNG");
                Path path = resource.getFile().toPath();
                byte[] imageBytes = Files.readAllBytes(path);
                listing.setPfp(imageBytes);
            }
            listingRepository.save(listing);
        } catch (IOException e) {
            logger.warn("Error reading the image file: ", e);
        }
    }

    public void updateListing(long listingId, Listing listing) {
        Listing existing = getListingById(listingId);
        existing.setName(listing.getName());
        existing.setTag(listing.getTag());
        existing.setDescription(listing.getDescription());
        existing.setPrice(listing.getPrice());

        listingRepository.save(existing);
    }

    public List<Listing> getListingBySearch(String name) {return listingRepository.findByNameContainingIgnoreCase(name); }

    public void deleteListingById(long sellerId, long listingId) {
        List<Cart> allCarts = cartService.getAllCarts();
        for(Cart cart:allCarts){
            cartService.removeListing(cart.getCartId(), listingId);
        }
        sellerService.deleteToSellerList(sellerId, listingId);
        listingRepository.deleteById(listingId);
    }

}

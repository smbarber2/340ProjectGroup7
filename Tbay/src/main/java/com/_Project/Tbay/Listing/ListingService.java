package com._Project.Tbay.Listing;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartService;
import com._Project.Tbay.Seller.SellerService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Listing getListingById(long listingId) {
        return listingRepository.findById(listingId).orElse(null);
    }

    public void addNewListing(Listing listing){
        listingRepository.save(listing);
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

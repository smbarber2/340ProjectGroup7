package com._Project.Tbay.Seller;

import com._Project.Tbay.Cart.CartRepository;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.User.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Lazy
    @Autowired
    private ListingService listingService;

    public void addNewSeller(Seller seller){
        seller.setCreationDate(new Date(System.currentTimeMillis()));
        sellerRepository.save(seller);
    }

    public Seller getSellerById(long sellerId) {
        return sellerRepository.findById(sellerId).orElse(null);
    }

    public List<Listing> getSellerList(long sellerId, String listingType){
        List<Listing> list = new ArrayList<>();
        List<Integer> deserializedList = switch (listingType) {
            case "listings" -> getSellerById(sellerId).getSellerListings();
            case "completed" -> getSellerById(sellerId).getCompletedOrders();
            case "incoming" -> getSellerById(sellerId).getIncomingOrders();
            default -> null;
        };

        if(deserializedList!=null){
            for(int val: deserializedList){
                list.add(listingService.getListingById(val));
            }
        }
        return list;
    }

    public void updateSellerListing(long sellerId){
        Seller seller = getSellerById(sellerId);
        List<Listing> listings = listingService.getAllListings();
        List<Integer> sellerListings = new ArrayList<>();

        for(Listing listing:listings){
            if(listing.getSellerId()==sellerId){
                sellerListings.add((int)listing.getListingId());
            }
        }
        seller.setSellerListings(sellerListings);
        sellerRepository.save(seller);
    }

    public void addToSellerList(long sellerId, long listingId, String listingType){
        Seller seller = getSellerById(sellerId);
        updateSellerListing(sellerId);
        List<Integer> list = switch (listingType) {
            case "listings" -> seller.getSellerListings();
            case "completed" -> seller.getCompletedOrders();
            case "incoming" -> seller.getIncomingOrders();
            default -> null;
        };
        if(list==null){
            list = new ArrayList<>();
        }
        list.add((int) listingId);
        switch (listingType){
            case "listings":
                seller.setSellerListings(list);
                break;
            case "completed":
                seller.setCompletedOrders(list);
                break;
            case "incoming":
                seller.setIncomingOrders(list);
                break;
        }
        sellerRepository.save(seller);
    }

    public void deleteToSellerList(long sellerId, long listingId){
        Seller seller = getSellerById(sellerId);
        List<Integer> list = getSellerById(sellerId).getSellerListings();
        list.remove(Integer.valueOf((int) listingId));
        seller.setSellerListings(list);
        sellerRepository.save(seller);
    }
}

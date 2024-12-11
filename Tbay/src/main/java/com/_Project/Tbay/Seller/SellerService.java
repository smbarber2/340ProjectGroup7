package com._Project.Tbay.Seller;

import com._Project.Tbay.Cart.CartRepository;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.Orders.Order;
import com._Project.Tbay.Orders.OrderService;
import com._Project.Tbay.User.User;
import jakarta.transaction.Transactional;
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
    @Lazy
    @Autowired
    private OrderService orderService;



    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);

    public void addNewSeller(Seller seller){
        try {
            seller.setCreationDate(new Date(System.currentTimeMillis()));
            if(seller.getPfp()==null){
                ClassPathResource resource = new ClassPathResource("static/pfp.png");
                Path path = resource.getFile().toPath();
                byte[] imageBytes = Files.readAllBytes(path);
                seller.setPfp(imageBytes);
            }
            sellerRepository.save(seller);
        } catch (IOException e) {
            logger.warn("Error reading the image file: ", e);
        }
    }

    public Seller getSellerById(long sellerId) {
        return sellerRepository.findById(sellerId).orElse(null);
    }

    public List<Listing> getSellerList(long sellerId){
        Seller seller = getSellerById(sellerId);
        List<Listing> list = new ArrayList<>();
        List<Integer> oldList = (seller.getSellerListings() != null) ? seller.getSellerListings() : new ArrayList<>();
        if(oldList!=null){
            for(int val: oldList){
                if(listingService.getListingById(val)!=null){
                    list.add(listingService.getListingById(val));
                }
            }
        }
        return list;
    }

    public List<Order> getSellerIncomingOrders(long sellerId){
        Seller seller = getSellerById(sellerId);
        List<Order> list = new ArrayList<>();
        List<Integer> oldList = (seller.getIncomingOrders() != null) ? seller.getIncomingOrders() : new ArrayList<>();
        if(oldList!=null){
            for(int val: oldList){
                if(orderService.getOrderById(val)!=null){
                    list.add(orderService.getOrderById(val));
                }
            }
        }
        return list;
    }

    public List<Order> getSellerCompletedOrders(long sellerId){
        Seller seller = getSellerById(sellerId);
        List<Order> list = new ArrayList<>();
        List<Integer> oldList = (seller.getCompletedOrders() != null) ? seller.getCompletedOrders() : new ArrayList<>();
        if(oldList!=null){
            for(int val: oldList){
                if(orderService.getOrderById(val)!=null){
                    list.add(orderService.getOrderById(val));
                }
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

    public void addOrder(long sellerId, long orderId){
        Seller seller = getSellerById(sellerId);
        List<Integer> list = (seller.getIncomingOrders() != null) ? seller.getIncomingOrders() : new ArrayList<>();
        list.add((int) orderId);
        seller.setIncomingOrders(list);
        sellerRepository.save(seller);
    }

    public void removeOrder(long sellerId, long orderId){
        Seller seller = getSellerById(sellerId);
        List<Integer> list = (seller.getIncomingOrders() != null) ? seller.getIncomingOrders() : new ArrayList<>();
        list.remove(Integer.valueOf((int) orderId));
        seller.setIncomingOrders(list);
        sellerRepository.save(seller);
    }

    public void addCompletedOrder(long sellerId, long orderId){
        Seller seller = getSellerById(sellerId);
        List<Integer> list = (seller.getCompletedOrders() != null) ? seller.getCompletedOrders() : new ArrayList<>();
        list.add((int) orderId);
        seller.setCompletedOrders(list);
        orderService.orderCompletion(orderId);
        sellerRepository.save(seller);
    }

    public void addToSellerList(long sellerId, long listingId){
        Seller seller = getSellerById(sellerId);
        updateSellerListing(sellerId);
        List<Integer> list = (seller.getSellerListings() != null) ? seller.getSellerListings() : new ArrayList<>();
        seller.setSellerListings(list);
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

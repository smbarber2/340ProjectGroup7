package com._Project.Tbay.User;

import com._Project.Tbay.Cart.CartRepository;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.Report.Report;
import com._Project.Tbay.Seller.SellerService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    @Lazy
    private ListingService listingService;

    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void addNewUser(User user){
        user.setCreationDate(new Date(System.currentTimeMillis()));
        try {
            if(user.getPfp()==null){
                ClassPathResource resource = new ClassPathResource("static/pfp.png");
                Path path = resource.getFile().toPath();
                byte[] imageBytes = Files.readAllBytes(path);
                user.setPfp(imageBytes);
            }
            userRepository.save(user);
        } catch (IOException e) {
            logger.warn("Error reading the image file: ", e);
        }
        userRepository.save(user);
    }

    public void updateUser(long userId, User user) {
        User existing = getUserById(userId);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setBio(user.getBio());
        existing.setWishlist(user.getWishlist());
        userRepository.save(existing);
    }

    public void updateBan(long userId, User user) {
        User existing = getUserById(userId);
        existing.setBan(user.isBan());
        userRepository.save(existing);
    }

    public List<User> getAllBans() {
        return userRepository.findAll();
    }

    public List<Listing> getWishlist(long userId){
        List<Listing> wishlist = new ArrayList<>();
        List<Integer> userList = getUserById(userId).getWishlist();
        if(userList!=null){
            for(int val: userList){
                if(listingService.getListingById(val)!=null){
                    wishlist.add(listingService.getListingById(val));
                }
            }
        }
        return wishlist;
    }

    public void addToWishlist(long userId, long listingId){
        User user = getUserById(userId);
        if(user.getWishlist()==null){
            List<Integer> wishlist = new ArrayList<>();
            wishlist.add((int)listingId);
            user.setWishlist(wishlist);
        } else if(!user.getWishlist().contains((int)listingId)){
            List<Integer> wishlist = user.getWishlist();
            wishlist.add((int)listingId);
            user.setWishlist(wishlist);
        }
        userRepository.save(user);
    }

    public void removeFromWishlist(long userId, long listingId){
        User user = getUserById(userId);
        List<Integer> wishlist = user.getWishlist();
        wishlist.remove(Integer.valueOf((int)listingId));
        user.setWishlist(wishlist);
        userRepository.save(user);
    }

    public User getUserByEmail(String email){
        List<User> userList = getAllUsers();
        for(User user:userList){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public void clearWishlist(long userId){
        User user = getUserById(userId);
        user.setWishlist(null);
        userRepository.save(user);
    }

    public User getLastUser() {
        return userRepository.findFirstByOrderByCreationDateDesc();
    }

}

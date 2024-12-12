
package com._Project.Tbay;

import com._Project.Tbay.Admin.AdminService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.Seller.SellerService;
import com._Project.Tbay.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ListingService listingService;

    @GetMapping({"", "/", "/home", "app"})
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "log-in";
    }

    @GetMapping("/create-acct")
    public String createAcct() {
        return "create-acct";
    }

    @GetMapping("/register")
    public String register(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role){
        switch (role) {
            case "user":
                long userId = userService.getUserByEmail(email).getUserId();
                return "redirect:/user/homepage/" + userId;
            case "seller":
                long sellerId = sellerService.getSellerByEmail(email).getSellerId();
                return "redirect:/seller/homepage/" + sellerId;
            case "admin":
                long adminId = adminService.getAdminByEmail(email).getAdminId();
                return "redirect:/seller/homepage/" + adminId;
        }
        return "/login";
    }


    @GetMapping("/user/homepage/{userId}")
    public String homepageUser(@PathVariable long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("title", userId);

        String base64 = null;
        if (userService.getUserById(userId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(userService.getUserById(userId).getPfp());
        }

        List<Listing> listingList = userService.getWishlist(userId);
        if(listingList!=null){
            for (Listing listing : listingList) {
                if (listing.getPfp() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                    listing.setBase64Image(base64Image);
                }
            }
        }
        model.addAttribute("wishlist", listingList);

        List<Listing> recList = listingService.getAllListings();
        List<Integer> wishlist = userService.getUserById(userId).getWishlist();
        if(wishlist!=null){
            recList.removeIf(listing -> wishlist.contains((int)listing.getListingId()));
        }

        for (Listing listing : recList) {
            if (listing.getPfp() != null) {
                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                listing.setBase64Image(base64Image);
            }
        }
        model.addAttribute("recList", recList);

        model.addAttribute("profilePic", base64);
        return "homepage";
    }

    @GetMapping("/seller/homepage/{sellerId}")
    public String homepageSeller(@PathVariable long sellerId, Model model) {
        model.addAttribute("seller", sellerService.getSellerById(sellerId));
        model.addAttribute("title", sellerId);

        List<Listing> listingList = sellerService.getSellerList(sellerId);
        for (Listing listing : listingList) {
            if (listing.getPfp() != null) {
                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                listing.setBase64Image(base64Image);
            }
        }
        model.addAttribute("listingList", listingList);

        List<Listing> recList = listingService.getAllListings();
        List<Integer> sellerList = sellerService.getSellerById(sellerId).getSellerListings();
        recList.removeIf(listing -> sellerList.contains((int)listing.getListingId()));
        for (Listing listing : recList) {
            if (listing.getPfp() != null) {
                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                listing.setBase64Image(base64Image);
            }
        }
        model.addAttribute("recList", recList);

        String base64 = null;
        if (sellerService.getSellerById(sellerId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(sellerService.getSellerById(sellerId).getPfp());
        }
        model.addAttribute("profilePic", base64);

        return "sellerHomepage";
    }

//    @GetMapping("/search/{tag}")
//    public String tagSearch(Model model) {
//        model.addAttribute("user", userService.getUserById(userId));
//        model.addAttribute("title", userId);
//        return "homepage";}
    /*
    @GetMapping("/admin/{adminId}")
    public String profileA(@PathVariable long sellerId, Model model) {
        Model seller = model.addAttribute("seller", sellerId);
        return "profile";
    }

    @GetMapping("/admin/homepage/{adminId}")
    public String homepageAdmin(@PathVariable long adminId, Model model) {
        model.addAttribute("user", adminService.getAdminById(adminId));
        model.addAttribute("title", adminId);
        return "homepage";
    }
    */
}



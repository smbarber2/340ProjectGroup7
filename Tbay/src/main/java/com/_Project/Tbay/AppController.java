
package com._Project.Tbay;

import com._Project.Tbay.Admin.Admin;
import com._Project.Tbay.Admin.AdminService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.Seller.Seller;
import com._Project.Tbay.Seller.SellerService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        return switch (role) {
            case "user" -> {
                User user = userService.getUserByEmail(email);
                if(user!=null && user.getPassword().equals(password)){
                    yield "redirect:/user/homepage/" + user.getUserId();
                }
                yield "redirect:/login";
            }
            case "seller" -> {
                Seller seller = sellerService.getSellerByEmail(email);
                if(seller!=null && seller.getPassword().equals(password)){
                    yield "redirect:/seller/homepage/" + seller.getSellerId();
                }
                yield "redirect:/login";
            }
            case "admin" -> {
                Admin admin = adminService.getAdminByEmail(email);
                if(admin!=null && admin.getPassword().equals(password)){
                    yield "redirect:/admin/homepage/" + admin.getAdminId();
                }
                yield "redirect:/login";
            }
            default -> "redirect:/login";
        };
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
        if(sellerList!=null){
            recList.removeIf(listing -> sellerList.contains((int)listing.getListingId()));
        }
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


    @GetMapping("/searchResults")
    public String getListingsByName(@RequestParam("name") String name, @RequestParam("userId") long userId, Model model) {
        return "redirect:/results/"+ name + "/" + userId;
    }

    @GetMapping("/searchSellerResults")
    public String getSellerSearch(@RequestParam("name") String name, @RequestParam("sellerId") long sellerId, Model model) {
        return "redirect:/resultSeller/"+ name + "/" + sellerId;
    }

    @GetMapping("/resultSeller/{name}/{sellerId}")
    public String searchSellerString(Model model, @PathVariable long sellerId, @PathVariable String name){
        List<Listing> resultList = (name != null) ? listingService.getListingsByName(name) : listingService.getAllListings();
        List<Listing> newResultList = new ArrayList<>();
        for (Listing listing : resultList) {
            if (listing.getPfp() != null && listing.getSellerId()!=sellerId) {
                newResultList.add(listing);
                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                listing.setBase64Image(base64Image);
            }
        }

        model.addAttribute("resultList", newResultList);
        model.addAttribute("seller", sellerService.getSellerById(sellerId));

        String base64 = null;
        if (sellerService.getSellerById(sellerId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(sellerService.getSellerById(sellerId).getPfp());
        }
        model.addAttribute("profilePic", base64);

        return "resultSeller";
    }

    @GetMapping("/results/{name}/{userId}")
    public String searchString(Model model, @PathVariable long userId, @PathVariable String name){
        List<Listing> resultList = (name != null) ? listingService.getListingsByName(name) : listingService.getAllListings();

        for (Listing listing : resultList) {
            if (listing.getPfp() != null) {
                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                listing.setBase64Image(base64Image);
            }
        }

        model.addAttribute("resultList", resultList);
        model.addAttribute("user", userService.getUserById(userId));

        String base64 = null;
        if (userService.getUserById(userId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(userService.getUserById(userId).getPfp());
        }
        model.addAttribute("profilePic", base64);

        return "results";
    }


    @GetMapping("/admin/homepage/{adminId}")
    public String homepageAdmin(@PathVariable long adminId, Model model) {
        model.addAttribute("admin", adminService.getAdminById(adminId));
        model.addAttribute("title", adminId);

        String base64 = null;
        if (adminService.getAdminById(adminId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(adminService.getAdminById(adminId).getPfp());
        }

        model.addAttribute("profilePic", base64);
        return "admin-homepage";
    }

    @GetMapping("/logout")
    public String logOut(){
        return "redirect:/login";
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



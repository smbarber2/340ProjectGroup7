package com._Project.Tbay.Listing;

import com._Project.Tbay.Cart.CartService;
import com._Project.Tbay.Comments.CommentService;
import com._Project.Tbay.Seller.Seller;
import com._Project.Tbay.Seller.SellerController;
import com._Project.Tbay.Seller.SellerService;
import com._Project.Tbay.User.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/Listing")

public class ListingController {
    @Autowired
    private ListingService service;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    @GetMapping("/allListing")
    public String getAllListings(Model model){
        model.addAttribute("listingList", service.getAllListings());
        model.addAttribute("title", "All listing");
        return "ListingPage";
    }

    @GetMapping("/{listingId}")
    public String getListing(@PathVariable long listingId, Model model) {
        model.addAttribute("listing", service.getListingById(listingId));
        model.addAttribute("title", "Listing Details:"+listingId);
        model.addAttribute("seller", sellerService.getSellerById(service.getListingById(listingId).getSellerId()));
//        model.addAttribute("comment", commentService.getCommentbyListingId(service.getCommentbyListingId(listingId).get));

        String base64 = null;
        if (service.getListingById(listingId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(service.getListingById(listingId).getPfp());
        }
        model.addAttribute("listingImg", base64);

        return "indivListing";
    }

    @GetMapping("/seller/{listingId}")
    public String getSellerListing(@PathVariable long listingId, Model model) {
        Listing listing = service.getListingById(listingId);
        model.addAttribute("listing",listing);
        model.addAttribute("title", "Listing Details:"+listingId);
        model.addAttribute("seller", sellerService.getSellerById(service.getListingById(listingId).getSellerId()));

        String pfpBase64 = null;
        if (sellerService.getSellerById(listing.getSellerId()).getPfp() != null) {
            pfpBase64 = Base64.getEncoder().encodeToString(sellerService.getSellerById(listing.getSellerId()).getPfp());
        }
        model.addAttribute("profilePic", pfpBase64);

        String base64 = null;
        if (service.getListingById(listingId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(service.getListingById(listingId).getPfp());
        }
        model.addAttribute("listingImg", base64);

        return "indivListingSeller";
    }

    @GetMapping("/showNew/{sellerId}")
    public String showNewListing(@PathVariable long sellerId, Model model){
        model.addAttribute("seller", sellerService.getSellerById(sellerId));
        return "indivListingAdd";
    }

    @PostMapping("/newListing")
    public String addNewListing(@RequestParam("sellerId") long sellerId, Listing listing){
        service.addNewListing(listing);
        sellerService.addToSellerList(sellerId, listing.getListingId(), "listings");
        return "redirect:/seller/"+sellerId;
    }

    @GetMapping("/updateListing/{listingId}")
    public String showUpdate(@PathVariable long listingId, Model model) {
        Listing listing = service.getListingById(listingId);
        model.addAttribute("listing", listing);
        model.addAttribute("seller", sellerService.getSellerById(service.getListingById(listingId).getSellerId()));

        String pfpBase64 = null;
        if (sellerService.getSellerById(listing.getSellerId()).getPfp() != null) {
            pfpBase64 = Base64.getEncoder().encodeToString(sellerService.getSellerById(listing.getSellerId()).getPfp());
        }
        model.addAttribute("profilePic", pfpBase64);

        String base64 = null;
        if (service.getListingById(listingId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(service.getListingById(listingId).getPfp());
        }
        model.addAttribute("listingImg", base64);

       return "indivListingEdit";
    }

    @PostMapping("/update")
    public String updateListing(@ModelAttribute Listing listing, @RequestParam long listingId) {
        Listing newListing = service.getListingById(listingId);

        newListing.setName(listing.getName());
        newListing.setTag(listing.getTag());
        newListing.setDescription(listing.getDescription());
        newListing.setPrice(listing.getPrice());

        service.addNewListing(newListing);
        cartService.updateCartListings(listing.getListingId());
        return "redirect:/seller/sellerListings/" + newListing.getSellerId();
    }

    @GetMapping("/delete/{listingId}")
    public String deleteListingById(@PathVariable long listingId) {
        long sellerId = service.getListingById(listingId).getSellerId();
        service.deleteListingById(sellerId, listingId);
        return "redirect:/seller/sellerListings/" + sellerId;
    }

    @GetMapping("/search") // /search?contains= input
    public String getListingBySearch(@RequestParam(name = "contains", defaultValue = "unspecified") String name, Model model) {
        model.addAttribute("listingList", service.getListingBySearch(name));
        model.addAttribute("title", "Name:" + name);
        return "ListingPage";
    }

    @PostMapping("/uploadImage")
    public String upload(Model model, @RequestParam("file") MultipartFile file, @RequestParam("listingId") long listingId) {
        Listing listing = service.getListingById(listingId);

        try {
            byte[] imageBytes = file.getBytes();
            listing.setPfp(imageBytes);
            service.addNewListing(listing);
        } catch (Exception e) {
            logger.warn("An exception was thrown:", e);
        }
        return "redirect:/Listing/updateListing/" + listingId;
    }


}

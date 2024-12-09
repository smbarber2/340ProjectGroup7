package com._Project.Tbay.Seller;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartController;
import com._Project.Tbay.Cart.CartService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.beans.PropertyEditorSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller")

public class SellerController {
    @Autowired
    private SellerService service;
    @Autowired
    private ListingService listingService;

    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    @GetMapping("/{sellerId}")
    public String getSellerById(@PathVariable long sellerId, Model model) {
        model.addAttribute("seller", service.getSellerById(sellerId));
        model.addAttribute("title", sellerId);
        List<Listing> listingList = service.getSellerList(sellerId, "listings");
        for (Listing listing : listingList) {
            if (listing.getPfp() != null) {
                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                listing.setBase64Image(base64Image);
            }
        }
        model.addAttribute("listingList", listingList);

        String base64 = null;
        if (service.getSellerById(sellerId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(service.getSellerById(sellerId).getPfp());
        }
        model.addAttribute("profilePic", base64);

        return "sellerpage";
    }

    @GetMapping("/sellerListings/{sellerId}")
    public String getUserById(@PathVariable long sellerId, Model model) {
        model.addAttribute("seller", service.getSellerById(sellerId));
        model.addAttribute("title", sellerId);

        List<Listing> listingList = service.getSellerList(sellerId, "listings");
        for (Listing listing : listingList) {
            if (listing.getPfp() != null) {
                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                listing.setBase64Image(base64Image);
            }
        }
        model.addAttribute("listingList", listingList);

        String base64 = null;
        if (service.getSellerById(sellerId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(service.getSellerById(sellerId).getPfp());
        }
        model.addAttribute("profilePic", base64);

        return "ListingPage";
    }

    @GetMapping("/update/{sellerId}")
    public String showUpdate(@PathVariable long sellerId, Model model) {
        model.addAttribute("seller", service.getSellerById(sellerId));
        model.addAttribute("title", sellerId);
        model.addAttribute("listingList", service.getSellerList(sellerId, "listings"));

        String base64 = null;
        if (service.getSellerById(sellerId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(service.getSellerById(sellerId).getPfp());
        }
        model.addAttribute("profilePic", base64);

        return "sellerUpdate";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("sellerListings");
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Seller seller, @RequestParam long sellerId) {
        Seller newSeller = service.getSellerById(sellerId);

        newSeller.setName(seller.getName());
        newSeller.setEmail(seller.getEmail());
        newSeller.setBio(seller.getBio());

        service.addNewSeller(newSeller);
        return "redirect:/seller/" + seller.getSellerId();
    }

    public static byte[] imageToByteArray(String imagePath) throws Exception {
        BufferedImage image = ImageIO.read(new File(imagePath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos); // Specify image format as needed
        return baos.toByteArray();
    }

    @PostMapping("/uploadImage")
    public String upload(Model model, @RequestParam("file") MultipartFile file, @RequestParam("sellerId") long sellerId) {
        Seller seller = service.getSellerById(sellerId);

        try {
            byte[] imageBytes = file.getBytes();
            seller.setPfp(imageBytes);
            service.addNewSeller(seller);
        } catch (Exception e) {
            logger.warn("An exception was thrown:", e);
        }
        return "redirect:/seller/update/" + sellerId;
    }



}

package com._Project.Tbay.User;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Report.Report;
import com._Project.Tbay.Seller.Seller;
import com._Project.Tbay.Seller.SellerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private CartService cartService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/new")
    public String addNewUser(User user){
        Cart cart = new Cart();
        cartService.addNewCart(cart);
        user.setCartId(cart.getCartId());
        service.addNewUser(user);
        cart.setUserId(user.getUserId());
        cartService.addNewCart(cart);
        return "redirect:/user/homepage/"+user.getUserId();
    }

    @GetMapping("/{userId}")
    public String profile(@PathVariable long userId, Model model) {
        model.addAttribute("user", service.getUserById(userId));
        model.addAttribute("title", userId);

        List<Listing> wishlist = service.getWishlist(userId);

        for (Listing listing : wishlist) {
            if (listing.getPfp() != null) {
                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
                listing.setBase64Image(base64Image);
            }
        }
        model.addAttribute("wishlist", wishlist);

        String base64 = null;
        if (service.getUserById(userId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(service.getUserById(userId).getPfp());
        }
        model.addAttribute("profilePic", base64);

        return "profile";
    }

    @GetMapping("/orders/{userId}")
    public String showOrders(){
        return "sellerOrders";
    }


    @GetMapping("/update/{userId}")
    public String showUpdateForm(@PathVariable long userId, Model model){
        model.addAttribute("user", service.getUserById(userId));
        model.addAttribute("title", userId);

        String base64 = null;
        if (service.getUserById(userId).getPfp() != null) {
            base64 = Base64.getEncoder().encodeToString(service.getUserById(userId).getPfp());
        }
        model.addAttribute("profilePic", base64);
        return "edit-profile";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("wishlist");
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam long userId, User user, Model model) {
        model.addAttribute("user", service.getUserById(userId));
        model.addAttribute("title", userId);
        service.updateUser(userId, user);
        return "redirect:/users/" + userId;
    }

    @PostMapping("/uploadImage")
    public String upload(Model model, @RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) {
        User user = service.getUserById(userId);

        try {
            byte[] imageBytes = file.getBytes();
            user.setPfp(imageBytes);
            service.addNewUser(user);
        } catch (Exception e) {
            logger.warn("An exception was thrown:", e);
        }
        return "redirect:/users/update/" + userId;
    }

    @GetMapping("/ban/{userId}")
    public String showBanForm(@PathVariable int userId, Model model){
        model.addAttribute("user", service.getUserById(userId));
        return "ban-create";
    }

    @GetMapping("/banAll")
    public String getAllBans(Model model) {
        model.addAttribute("banList", service.getAllBans());
        model.addAttribute("title", "All Bans");
        return "all-bans";
    }

    @PostMapping("/banUpdate")
    public String updateBan(User user) {
        service.updateBan(user.getUserId(), user);
        return "redirect:/admin/banlist";
    }
}

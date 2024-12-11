package com._Project.Tbay.User;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartService;
import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Report.Report;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private CartService cartService;

    @PostMapping("/new")
    public String addNewUser(User user){
        Cart cart = new Cart();
        cartService.addNewCart(cart);
        cart.setUserId(user.getUserId());
        user.setCartId(cart.getCartId());
        service.addNewUser(user);
        return "homepage";
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

//    @GetMapping("/checkout/{userId}")
//    public String checkoutShow(@PathVariable long userId, Model model){
//        model.addAttribute("user", service.getUserById(userId));
//        model.addAttribute("title", userId);
//
//        List<Listing> cart = service.getCart(userId);
//
//        for (Listing listing : cart) {
//            if (listing.getPfp() != null) {
//                String base64Image = Base64.getEncoder().encodeToString(listing.getPfp());
//                listing.setBase64Image(base64Image);
//            }
//        }
//        model.addAttribute("cart", cart);
//
//        return "checkout";
//    }

    @GetMapping("/update/{userId}")
    public String showUpdateForm(@PathVariable long userId, Model model){
        model.addAttribute("user", service.getUserById(userId));
        model.addAttribute("title", userId);
        return "edit-profile";
    }
    @PostMapping("/update")
    public String updateUser(User user) {
        service.updateUser(user.getUserId(), user);
        return "redirect:/{userId}";
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
        return "all-bans"; //Like the table from hw
    }

    @PostMapping("/banUpdate")
    public String updateBan(User user) {
        service.updateBan(user.getUserId(), user);
        return "redirect:/admin/banlist";
    }

}

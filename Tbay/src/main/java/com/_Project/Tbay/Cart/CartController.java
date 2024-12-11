package com._Project.Tbay.Cart;

import com._Project.Tbay.Listing.Listing;
import com._Project.Tbay.Listing.ListingService;
import com._Project.Tbay.Orders.OrderRepository;
import com._Project.Tbay.Orders.OrderService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService service;
    @Autowired
    private ListingService listingService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAllCarts(Model model){
        model.addAttribute("cartList",service.getAllCarts());
        model.addAttribute("title", "All Carts");
        return "cartList";
    }

    @PostMapping("/addListing")
    public String addListing(@RequestParam("cartId") long cartId, @RequestParam("listingId") long listingId) {
        service.addListing(cartId, listingId);
        return "redirect:/carts/"+cartId;
    }

    @GetMapping("/{cartId}")
    public String getCart(@PathVariable long cartId, Model model){
        model.addAttribute("cart", service.getCartById(cartId));
        model.addAttribute("title", "Cart:"+cartId);

        User user = userService.getUserById(service.getCartById(cartId).getUserId());
        model.addAttribute("user", user);

        List<Listing> list = new ArrayList<>();
        if(service.getCartById(cartId).getCartList() !=null) {
            List<Integer> deserializedList = service.getCartById(cartId).getCartList();
            for(int val: deserializedList){
                list.add(listingService.getListingById(val));
            }
        }
        model.addAttribute("listingList", list);

        String pfpBase64 = null;
        if (user.getPfp() != null) {
            pfpBase64 = Base64.getEncoder().encodeToString(user.getPfp());
        }
        model.addAttribute("profilePic", pfpBase64);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("cartId") long cartId){
        Cart cart = service.getCartById(cartId);
        long userId = service.getCartById(cartId).getUserId();

        List<Integer> cartList = cart.getCartList();
        if(cartList!= null) {
            for (Integer integer : cartList) {
                Listing listing = listingService.getListingById(integer);
                orderService.createOrder(listing.getSellerId(), listing.getListingId(), userId);
            }
        } else {
            return "redirect:/users/"+ userId;
        }
        userService.clearWishlist(userId);
        service.clearCart(cart);
        return "redirect:/users/"+ userId;
    }

    @PostMapping("/new")
    public void addNewCart(@RequestBody Cart cart) {
        service.addNewCart(cart);
    }

    @GetMapping("/update/{cartId}")
    public void showUpdateCart(@PathVariable long cartId, Model model) {
        model.addAttribute("cart",service.getCartById(cartId));
    }

    @PostMapping("/removeListing")
    public String updateCart(@RequestParam("cartId") long cartId,@RequestParam("listingId") long listingId) {
        service.removeListing(cartId, listingId);
        return "redirect:/carts/"+cartId;
    }

    @GetMapping("/delete/{cartId}")
    public String deleteById(@PathVariable long cartId) {
        service.deleteCartById(cartId);
        return "redirect:/carts/all";
    }

}

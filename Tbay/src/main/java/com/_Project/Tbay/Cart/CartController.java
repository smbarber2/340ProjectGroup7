package com._Project.Tbay.Cart;

import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService service;

    @GetMapping("/all")
    public String getAllCarts(Model model){
        model.addAttribute("cartList",service.getAllCarts());
        model.addAttribute("title", "All Carts");
        return "cartList";
    }

    @GetMapping("/{cartId}")
    public String getCart(@PathVariable long cartId, Model model){
        model.addAttribute("cart", service.getCartById(cartId));
        model.addAttribute("title", "Cart:"+cartId);
        return "checkout";
    }

    @PostMapping("/new")
    public void addNewCart(@RequestBody Cart cart) {
        service.addNewCart(cart);
    }

    @GetMapping("/update/{cartId}")
    public void showUpdateCart(@PathVariable long cartId, Model model) {
        model.addAttribute("cart",service.getCartById(cartId));
    }

    @PostMapping("/update")
    public String updateCart(Cart cart) {
        service.addNewCart(cart);
        return "redirect:/carts/"+cart.getCartId();
    }

    @GetMapping("/delete/{cartId}")
    public String deleteById(@PathVariable long cartId) {
        service.deleteCartById(cartId);
        return "redirect:/carts/all";
    }

}

package com._Project.Tbay.Cart;

import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService service;

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable long cartId){return service.getCartById(cartId);}

    @PostMapping("/update/{cartId}")
    public Cart updateCart(@PathVariable long cartId, @RequestBody Cart cart) {
        service.updateCart(cartId, cart);
        return service.getCartById(cartId);
    }

    //DELETE existing User
    @DeleteMapping("/delete/{cartId}")
    public List<Cart> deleteById(@PathVariable long cartId) {
        service.deleteCartById(cartId);
        return service.getAllCarts();
    }

}

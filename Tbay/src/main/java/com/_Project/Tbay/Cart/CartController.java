package com._Project.Tbay.Cart;

import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService service;

    @GetMapping("/all")
    public List<Cart> getAllCarts(){
        return service.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable long cartId){return service.getCartById(cartId);}

    @PostMapping("/new")
    public List<Cart> addNewCart(@RequestBody Cart cart) {
        service.addNewCart(cart);
        return service.getAllCarts();
    }

    @PutMapping("/update/{cartId}")
    public Cart updateCart(@PathVariable long cartId, @RequestBody Cart cart) {
        service.updateCart(cartId, cart);
        return service.getCartById(cartId);
    }

    @DeleteMapping("/delete/{cartId}")
    public List<Cart> deleteById(@PathVariable long cartId) {
        service.deleteCartById(cartId);
        return service.getAllCarts();
    }

}

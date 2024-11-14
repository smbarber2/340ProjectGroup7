package com._Project.Tbay.User;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private CartService cartService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable long userId){return service.getUserById(userId);}

    @PostMapping("/new")
    public List<User> addNewUser(@RequestBody User user){
        Cart cart = new Cart();
        cartService.addNewCart(cart);
        user.setCartId(cart.getCartId());
        service.addNewUser(user);
        return service.getAllUsers();
    }

    @PutMapping("/update/{userId}")
    public User updateUser(@PathVariable long userId, @RequestBody User user) {
        service.updateUser(userId, user);
        return service.getUserById(userId);
    }

}

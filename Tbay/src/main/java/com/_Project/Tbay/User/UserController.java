package com._Project.Tbay.User;

import com._Project.Tbay.Cart.Cart;
import com._Project.Tbay.Cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private CartService cartService;

//    @GetMapping("/all")
//    public List<User> getAllUsers(){
//        return service.getAllUsers();
//    }

    //Returns all users
//    @GetMapping("/all")
//    public String getAllUsers(Model model){
//        model.addAttribute("userList", service.getAllUsers());
//        return "user-list";
//    }


//    @GetMapping("/{userId}")
//    public User getOneUser(@PathVariable long userId){return service.getUserById(userId);}

    //Returns information about a specified user, less detailed than admin view
//    @GetMapping("/{userId}")
//        public User getOneUser(@PathVariable long userId, Model model){
//        model.addAttribute("user", service.getUserById(userId));
//        return "user-details";
//    }


//    @PostMapping("/new")
//    public List<User> addNewUser(@RequestBody User user){
//        Cart cart = new Cart();
//        cartService.addNewCart(cart);
//        user.setCartId(cart.getCartId());
//        service.addNewUser(user);
//        return service.getAllUsers();
//    }

    //Shows the create account form page
    @PostMapping("/createform")
    public String showCreateForm(){
        return "create-acct";
    }

//    @PutMapping("/update/{userId}")
//    public User updateUser(@PathVariable long userId, @RequestBody User user) {
//        service.updateUser(userId, user);
//        return service.getUserById(userId);
//    }
    @PostMapping("/update")
    public String updateUser(User user) {
        service.saveUser(user);
        return "redirect:/profile/" + user.getUserId();
    }


}

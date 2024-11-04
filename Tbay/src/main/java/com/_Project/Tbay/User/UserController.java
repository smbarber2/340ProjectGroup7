package com._Project.Tbay.User;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    /**
     * Get a specific User by Id.
     *
     * @param userId the unique Id for a User.
     * @return One User object.
     */
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable int userId){return service.getUserById(userId);}

    @PostMapping("/new")
    public List<User> addNewUser(@RequestBody User user){service.addNewUser(user); return service.getAllUsers();}

    @PutMapping("/update/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User user) {
        service.updateUser(userId, user);
        return service.getUserById(userId);
    }

}

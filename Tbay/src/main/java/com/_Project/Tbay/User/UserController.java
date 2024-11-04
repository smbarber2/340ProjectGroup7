package com._Project.Tbay.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }
}

package com._Project.Tbay.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     Fetch all Users.*
     @return the list of all Users.*/
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    /**
     Add New User
     @param user the new user to add **/
    public void addNewUser(User user){userRepository.save(user);}

    /**
     Update a User
     @param userId the new user to add
     @param user the new User details
     **/
    public void updateUser(long userId, User user) {
        User existing = getUserById(userId);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());


        //Technically the 4 lines above are not necessary because the save method merges by default.
        userRepository.save(existing);
    }



}

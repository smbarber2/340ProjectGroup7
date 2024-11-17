package com._Project.Tbay.User;

import com._Project.Tbay.Cart.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void addNewUser(User user){
        user.setCreationDate(new Date(System.currentTimeMillis()));
        userRepository.save(user);
    }

    public void updateUser(long userId, User user) {
        User existing = getUserById(userId);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setBio(user.getBio());
        userRepository.save(existing);
    }

    public User getLastUser() {
        return userRepository.findFirstByOrderByCreationDateDesc();
    }

}

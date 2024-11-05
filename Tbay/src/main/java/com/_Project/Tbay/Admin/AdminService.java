package com._Project.Tbay.Admin;

import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<User> getAllUsers() {
        return adminRepository.findAll();
    }

    public User getUserById(long userId) {
        return adminRepository.findById(userId).orElse(null);
    }

    public void deleteUserById(long userId) {
        adminRepository.deleteById(userId);
    }

}

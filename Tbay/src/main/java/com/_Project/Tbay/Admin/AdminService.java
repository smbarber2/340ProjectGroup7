package com._Project.Tbay.Admin;

import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    @Lazy
    private UserRepository userRepository;

    public Admin getAdminById(long adminId){
        return adminRepository.findById(adminId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

//    public void banUser(long userId, Model model) {
//        Model existing = getUserById(userId);
//        existing.setBan(model.getBan());
//        userRepository.save(existing);
//    }

}

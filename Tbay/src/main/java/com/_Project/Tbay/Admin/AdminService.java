package com._Project.Tbay.Admin;

import com._Project.Tbay.Seller.Seller;
import com._Project.Tbay.Seller.SellerService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    @Lazy
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);

    public Admin getAdminById(long adminId){
        return adminRepository.findById(adminId).orElse(null);
    }

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public void addNewAdmin(Admin admin){
        try {
            if(admin.getPfp()==null){
                ClassPathResource resource = new ClassPathResource("static/pfp.png");
                Path path = resource.getFile().toPath();
                byte[] imageBytes = Files.readAllBytes(path);
                admin.setPfp(imageBytes);
            }
            adminRepository.save(admin);
        } catch (IOException e) {
            logger.warn("Error reading the image file: ", e);
        }
    }

    public Admin getAdminByEmail(String email){
        List<Admin> adminList = getAllAdmins();
        for(Admin admin:adminList){
            if(admin.getEmail().equals(email)){
                return admin;
            }
        }
        return null;
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

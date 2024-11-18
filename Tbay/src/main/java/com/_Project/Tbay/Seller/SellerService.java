package com._Project.Tbay.Seller;

import com._Project.Tbay.Cart.CartRepository;
import com._Project.Tbay.User.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public void addNewSeller(Seller seller){
        seller.setCreationDate(new Date(System.currentTimeMillis()));
        sellerRepository.save(seller);
    }

    public Seller getSellerById(long sellerId) {
        return sellerRepository.findById(sellerId).orElse(null);
    }
}

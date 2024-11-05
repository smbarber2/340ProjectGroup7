package com._Project.Tbay.Cart;

import com._Project.Tbay.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartById(long cartId) { return cartRepository.findById(cartId).orElse(null);}

    public void updateCart(long cartId, Cart cart) {
        Cart existing = getCartById(cartId);
        existing.setCartList(cart.getCartList());

        cartRepository.save(existing);
    }
}

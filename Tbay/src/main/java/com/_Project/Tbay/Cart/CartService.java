package com._Project.Tbay.Cart;

import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartById(long cartId) { return cartRepository.findById(cartId).orElse(null);}

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public void deleteCartById(long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void updateCart(long cartId, Cart cart) {
        Cart existing = getCartById(cartId);
        existing.setCartList(cart.getCartList());
        cartRepository.save(existing);
    }

    public void addNewCart(Cart cart){
        cartRepository.save(cart);
    }

    public void removeListing(long cartId, long listingId) {
        Cart existing = getCartById(cartId);
        List<Integer> list = existing.getCartList();
        list.remove(Integer.valueOf((int)listingId));
        existing.setCartList(list);
        cartRepository.save(existing);
    }

}



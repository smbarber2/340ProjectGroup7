package com._Project.Tbay.Cart;

import com._Project.Tbay.User.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long cartId;


    public List<Integer> cartList;

    public float totalPrice;

    public int transactionId;



    public Cart(long cartId, List<Integer> cartList, float totalPrice, int transactionId){
        this.cartId = cartId;
        this.cartList = cartList;
        this.totalPrice = totalPrice;
        this.transactionId = transactionId;
    }

    public Cart(){}

    public Cart(long cartId){
        this.cartId = cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartList(List<Integer> cartList) {
        this.cartList = cartList;
    }

    public List<Integer> getCartList() {
        return cartList;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }
}

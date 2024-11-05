package com._Project.Tbay.User;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long userId;

    @Column(nullable=false)
    public String name;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private boolean auth;

    @Column(nullable=true)
    protected List<Integer> wishlist;

    @Column(nullable=false)
    protected String role;

    @Column(nullable=false)
    protected boolean status;

    @Column(nullable=true)
    protected long cartId;

    public User(long userId, String name, String password, String email, boolean auth, String role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.auth = auth;
        this.role = role;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWishlist(List<Integer> wishlist) {
        this.wishlist = wishlist;
   }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Integer> getWishlist() {
        return wishlist;
    }

    public String getRole() {
        return role;
    }

    public boolean isStatus() {
        return status;
    }

    public long getCartId() {
        return cartId;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth;
    }
}




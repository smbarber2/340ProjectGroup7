package com._Project.Tbay.User;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;
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

    @Column(nullable=false)
    private Date creationDate;

    @Column(nullable=true)
    protected List<Integer> wishlist;

    @Column(nullable=false)
    protected boolean status;

    @Column(nullable=true)
    protected boolean ban;

    @Column(nullable=true)
    protected long cartId;

    @Column(nullable=true)
    public String bio;

    @Column(nullable=true)
    public String pfp;

    public User(long userId, String name, String password, String email, boolean auth, boolean ban, boolean status, String pfp) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.auth = auth;
        this.ban = false;
        this.status = status;
        this.creationDate = creationDate;
        this.pfp = pfp;
    }

    public User(long userId, String name, String password, String email, boolean auth) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.auth = auth;
    }

    public User(){}

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


    public void setStatus(boolean status) {
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    //Banning stuff

    public void setBan(boolean ban) { this.ban = ban; }

    public boolean isBan() { return ban;}
}




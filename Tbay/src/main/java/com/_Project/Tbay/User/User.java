package com._Project.Tbay.User;

import jakarta.persistence.*;

@Entity
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userId;

    @Column(nullable=false)
    public String name;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String email;

    /*@Column(nullable=true)
    protected Array[] wishlist;*/

    @Column(nullable=false)
    protected String role;

    @Column(nullable=false)
    protected boolean status;

    @Column(nullable=true)
    protected int cartId;
}

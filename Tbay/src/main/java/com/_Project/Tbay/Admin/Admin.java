package com._Project.Tbay.Admin;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long adminId;

    @Column(nullable=false)
    public String name;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String email;

    @Lob
    @Column(nullable=true, columnDefinition="LONGBLOB")
    public byte[] pfp;

    public Admin(long adminId, String name, String password, byte[] pfp, String email) {
        this.adminId = adminId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.pfp = pfp;
    }

    public Admin() {}

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPfp() {
        return pfp;
    }

    public void setPfp(byte[] pfp) {
        this.pfp = pfp;
    }
}







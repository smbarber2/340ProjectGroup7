package com._Project.Tbay.security;

import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
public class CustomUserDetailService implements UserDetailsService{
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = repo.findByName(name).orElseThrow(()
                -> new UsernameNotFoundException(name + "not found"));
        ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), authList);
    }
}

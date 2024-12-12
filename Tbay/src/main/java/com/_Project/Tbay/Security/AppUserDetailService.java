//package com._Project.Tbay.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AppUserDetailService implements UserDetailsService {
//
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//            return userDetailsService().loadUserByUsername(username);
//        }
//
//        public UserDetailsService userDetailsService() {
//
//            UserDetails user
//                    = User.builder()
//                    .username("bilbo")
//                    .password(passwordEncoder().encode("no-sand"))
//                    .roles("PADAWAN")
//                    .build();
//
//            UserDetails mod
//                    = User.builder()
//                    .username("gollum")
//                    .password(passwordEncoder().encode("hello-there"))
//                    .roles("KNIGHT")
//                    .build();
//
//            UserDetails admin
//                    = User.builder()
//                    .username("gandalf")
//                    .password(passwordEncoder().encode("bigger-fish"))
//                    .roles("admin")
//                    .build();
//
//            return new InMemoryUserDetailsManager(user, mod, admin);
//        }
//
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//    }
//
//

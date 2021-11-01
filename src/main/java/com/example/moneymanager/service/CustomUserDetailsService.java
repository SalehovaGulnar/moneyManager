package com.example.moneymanager.service;

import com.example.moneymanager.model.CustomUserDetails;
import com.example.moneymanager.model.User;
import com.example.moneymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findUserByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("USer not found");
        else {
            UserDetails userDetails= new CustomUserDetails(user.get());
            return userDetails ;
        }
    }
}

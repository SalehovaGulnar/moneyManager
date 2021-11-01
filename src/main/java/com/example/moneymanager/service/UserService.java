package com.example.moneymanager.service;

import com.example.moneymanager.dao.UserDAO;
import com.example.moneymanager.model.User;
import com.example.moneymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserDAO {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username= ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findUserByEmail(username).get();
    }
}

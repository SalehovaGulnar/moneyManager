package com.example.moneymanager.dao;

import com.example.moneymanager.model.User;
import java.util.Optional;

public interface UserDAO {
    
    Optional<User> getUserByEmail(String email);

    void addUser(User user);
}

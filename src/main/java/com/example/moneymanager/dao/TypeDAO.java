package com.example.moneymanager.dao;

import com.example.moneymanager.model.Type;
import com.example.moneymanager.model.User;

import java.util.List;
import java.util.Optional;


public interface TypeDAO {

    List<Type> getAllTypeByLoggedUser(User user);

    List<Type> getAllTypeByUser(User user);

    void addType(Type type);

    void deleteTypeById(Integer id);

    Optional<Type> getTypeById(Integer id);
}

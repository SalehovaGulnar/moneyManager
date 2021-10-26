package com.example.moneymanager.dao;

import com.example.moneymanager.model.Type;

import java.util.List;
import java.util.Optional;


public interface TypeDAO {

    List<Type> getAllAdminTypes();

    List<Type> getAllUserTypes();

    void addType(Type type);

    void deleteTypeById(Integer id);

    Optional<Type> getTypeById(Integer id);
}

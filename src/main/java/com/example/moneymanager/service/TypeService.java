package com.example.moneymanager.service;

import com.example.moneymanager.dao.TypeDAO;
import com.example.moneymanager.model.Type;
import com.example.moneymanager.model.User;
import com.example.moneymanager.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService implements TypeDAO{

    @Autowired
    TypeRepository typeRepository;

    @Override
    public List<Type> getAllAdminTypes(User user) {
        return typeRepository.findTypeByUser(user);
    }

    @Override
    public void addType(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void deleteTypeById(Integer id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Optional<Type> getTypeById(Integer id) {
        return typeRepository.findById(id);
    }
}

package com.example.moneymanager.service;

import com.example.moneymanager.dao.TypeDAO;
import com.example.moneymanager.model.Category;
import com.example.moneymanager.model.Type;
import com.example.moneymanager.model.User;
import com.example.moneymanager.repository.TypeRepository;
import com.example.moneymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService implements TypeDAO{

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public List<Type> getAllTypeByLoggedUser(User user) {
        return typeRepository.findAllByUser_Id(user.getId());
    }

    @Override
    public List<Type> getAllByUserAndCategory(User user, Category category) {
        List<Integer> userList = new ArrayList<>();
        userList.add(user.getId());
        return typeRepository.findAllByUser_IdInAndCategory_Id(userList, category);
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

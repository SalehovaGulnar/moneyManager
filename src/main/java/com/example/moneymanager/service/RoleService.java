package com.example.moneymanager.service;

import com.example.moneymanager.dao.RoleDAO;
import com.example.moneymanager.model.Role;
import com.example.moneymanager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleDAO {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String role_name) {
        return roleRepository.findRoleByName(role_name);
    }
}

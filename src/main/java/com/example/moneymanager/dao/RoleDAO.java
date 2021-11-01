package com.example.moneymanager.dao;

import com.example.moneymanager.model.Role;

import java.util.Optional;

public interface RoleDAO {

     Role getRoleByName(String role_name);
}

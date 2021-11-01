package com.example.moneymanager.repository;

import com.example.moneymanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByName(String role_name);
}

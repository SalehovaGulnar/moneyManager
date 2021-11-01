package com.example.moneymanager.controller;

import com.example.moneymanager.model.Role;
import com.example.moneymanager.model.User;
import com.example.moneymanager.service.RoleService;
import com.example.moneymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public String getHome() {
        User loggedUser = userService.getLoggedUser();
        List<Role> roles = loggedUser.getRoles();
        if (roles.contains(roleService.getRoleByName("ROLE_ADMIN")))
            return "adminHome";
        else
            return "userHome";
    }
}

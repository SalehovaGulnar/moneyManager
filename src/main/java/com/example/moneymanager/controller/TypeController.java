package com.example.moneymanager.controller;

import com.example.moneymanager.dao.CategoryDAO;
import com.example.moneymanager.dao.TypeDAO;
import com.example.moneymanager.dto.TypeDTO;
import com.example.moneymanager.model.Category;
import com.example.moneymanager.model.Type;
import com.example.moneymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
public class TypeController {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    TypeDAO typeDAO;

    @Autowired
    UserService userService;

    @GetMapping("/types")
    public String getTypes(Model model) {
        model.addAttribute("categories", categoryDAO.getAllCategories());
        model.addAttribute("types", typeDAO.getAllTypeByLoggedUser(userService.getLoggedUser()));
        return "types";
    }

    @GetMapping("/type/add")
    public String getTypeAdd(Model model) {
        model.addAttribute("categoryDTO", categoryDAO.getAllCategories());
        model.addAttribute("typeDTO", new TypeDTO());
        return "typeAdd";
    }

    @PostMapping("/type/add")
    public String postTypeAdd(@ModelAttribute("typeDTO") TypeDTO typeDTO) {
        Type type = new Type();
        type.setId(typeDTO.getId());
        type.setName(typeDTO.getName());
        type.setUser(userService.getLoggedUser());
        Optional<Category> category = categoryDAO.getCategoryById(typeDTO.getCategoryId());
        type.setCategory(category.get());
        typeDAO.addType(type);
        return "redirect:/types";
    }

    @GetMapping("/type/delete/{id}")
    public String getTypeDelete(@PathVariable("id") Integer id) {
        typeDAO.deleteTypeById(id);
        return "redirect:/types";

    }

    @GetMapping("/type/update/{id}")
    public String getTypeUpdate(@PathVariable("id") Integer id, Model model) {
        Optional<Type> type = typeDAO.getTypeById(id);
        if(type.isPresent()) {
            TypeDTO typeDTO = new TypeDTO();
            typeDTO.setId(type.get().getId());
            typeDTO.setName(type.get().getName());
            typeDTO.setUserId(userService.getLoggedUser().getId());
            typeDTO.setCategoryId(type.get().getCategory().getId());
            model.addAttribute("categoryDTO", categoryDAO.getAllCategories());
            model.addAttribute("typeDTO", typeDTO);
            return "typeAdd";
        }
        else
            return "404";
    }
}

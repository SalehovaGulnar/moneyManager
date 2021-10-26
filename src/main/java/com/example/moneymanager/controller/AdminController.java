package com.example.moneymanager.controller;

import com.example.moneymanager.dao.CategoryDAO;
import com.example.moneymanager.dao.TypeDAO;
import com.example.moneymanager.dto.CategoryDTO;
import com.example.moneymanager.dto.TypeDTO;
import com.example.moneymanager.model.Category;
import com.example.moneymanager.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    TypeDAO typeDAO;

    @GetMapping("/admin")
    public String getAdmin() {
        return "adminHome";
    }

    //Categories section

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryDAO.getAllCategories());
        return "categories";
    }

    @GetMapping("/admin/category/add")
    public String getCategoryAdd(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "categoryAdd";
    }

    @PostMapping("/admin/category/add")
    public String postCategoryAdd(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        categoryDAO.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String getCategoryDelete(@PathVariable("id") Integer id) {

        categoryDAO.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/category/update/{id}")
    public String getCategoryAdd(@PathVariable("id") Integer id, Model model) {
        Optional<Category> category = categoryDAO.getCategoryById(id);
        if(category.isPresent()) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.get().getId());
            categoryDTO.setName(category.get().getName());
            model.addAttribute("categoryDTO", categoryDTO);
            return "categoryAdd";
        }
        else
            return "404";
    }

    //Types section

    @GetMapping("/admin/types")
    public String getTypes(Model model) {
        model.addAttribute("categories", categoryDAO.getAllCategories());
        model.addAttribute("types", typeDAO.getAllTypes());
        return "types";
    }

    @GetMapping("/admin/type/add")
    public String getTypeAdd(Model model) {
        model.addAttribute("categoryDTO", categoryDAO.getAllCategories());
        model.addAttribute("typeDTO", new TypeDTO());
        return "typeAdd";
    }

    @PostMapping("/admin/type/add")
    public String postTypeAdd(@ModelAttribute("typeDTO") TypeDTO typeDTO) {
        Type type = new Type();
        type.setId(typeDTO.getId());
        type.setName(typeDTO.getName());
        System.out.println("type name = " + type.getName());
        Optional<Category> category = categoryDAO.getCategoryById(typeDTO.getCategoryId());
        type.setCategory(category.get());
        typeDAO.addType(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/admin/type/delete/{id}")
    public String getTypeDelete(@PathVariable("id") Integer id) {
        typeDAO.deleteTypeById(id);
        return "redirect:/admin/types";

    }

    @GetMapping("/admin/type/update/{id}")
    public String getTypeDelete(@PathVariable("id") Integer id, Model model) {
        Optional<Type> type = typeDAO.getTypeById(id);
        if(type.isPresent()) {
            TypeDTO typeDTO = new TypeDTO();
            typeDTO.setId(type.get().getId());
            typeDTO.setName(type.get().getName());
            typeDTO.setCategoryId(type.get().getCategory().getId());
            model.addAttribute("categoryDTO", categoryDAO.getAllCategories());
            model.addAttribute("typeDTO", typeDTO);
            return "typeAdd";
        }
        else
            return "404";
    }
}

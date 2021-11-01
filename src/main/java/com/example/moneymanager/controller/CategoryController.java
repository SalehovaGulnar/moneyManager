package com.example.moneymanager.controller;

import com.example.moneymanager.dao.CategoryDAO;
import com.example.moneymanager.dto.CategoryDTO;
import com.example.moneymanager.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    CategoryDAO categoryDAO;

    @GetMapping("/categories")
    public String getCategories(Model model) {
    model.addAttribute("categories", categoryDAO.getAllCategories());
    return "categories";
    }

    @GetMapping("/category/add")
    public String getCategoryAdd(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "categoryAdd";
    }

    @PostMapping("/category/add")
    public String postCategoryAdd(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        categoryDAO.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/category/delete/{id}")
    public String getCategoryDelete(@PathVariable("id") Integer id) {

        categoryDAO.deleteCategoryById(id);
        return "redirect:/categories";
    }

    @GetMapping("/category/update/{id}")
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
}

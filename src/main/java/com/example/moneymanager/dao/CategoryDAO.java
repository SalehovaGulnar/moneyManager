package com.example.moneymanager.dao;

import com.example.moneymanager.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryDAO {

    List<Category> getAllCategories();

    void addCategory(Category category);

    void deleteCategoryById(Integer id);

    Optional<Category> getCategoryById(Integer id);

}

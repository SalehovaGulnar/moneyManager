package com.example.moneymanager.dto;

import com.example.moneymanager.model.Category;
import lombok.Data;

@Data
public class TypeDTO {

    private int id;

    private String name;

    private int categoryId;

    private int userId;
}

package com.example.moneymanager.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class TransactionDTO {

    private int id;

    private int categoryId;

    private int typeId;

    private Date date;

    private Double amount;

    private String description;

    private int userId;
}

package com.example.moneymanager.service;

import com.example.moneymanager.dao.TransactionDAO;
import com.example.moneymanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionDAO {

    @Autowired
    TransactionRepository transactionRepository;

}

package com.example.moneymanager.dao;

import com.example.moneymanager.model.Transaction;
import com.example.moneymanager.model.User;
import java.util.List;
import java.util.Optional;

public interface TransactionDAO {
    List<Transaction> getAllTransactionByLoggedUser(User user);

    void addTransaction(Transaction transaction);

    Optional<Transaction> getTransactionById(Integer id);

    void deleteTransactionById(Integer id);
}

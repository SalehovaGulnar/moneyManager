package com.example.moneymanager.repository;

import com.example.moneymanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUser_Id(Integer id);

}

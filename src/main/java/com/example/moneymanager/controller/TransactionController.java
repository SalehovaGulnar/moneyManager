package com.example.moneymanager.controller;

import com.example.moneymanager.dao.CategoryDAO;
import com.example.moneymanager.dao.TransactionDAO;
import com.example.moneymanager.dao.TypeDAO;
import com.example.moneymanager.dto.TransactionDTO;
import com.example.moneymanager.dto.TypeDTO;
import com.example.moneymanager.model.Category;
import com.example.moneymanager.model.Transaction;
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
public class TransactionController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    TypeDAO typeDAO;

    @Autowired
    TransactionDAO transactionDAO;

    @GetMapping("/transactions")
    public String getTypes(Model model) {
        model.addAttribute("categories", categoryDAO.getAllCategories());
        model.addAttribute("types", typeDAO.getAllTypeByLoggedUser(userService.getLoggedUser()));
        model.addAttribute("transactions", transactionDAO.getAllTransactionByLoggedUser(userService.getLoggedUser()));
        return "transactions";
    }

    @GetMapping("/transaction/add")
    public String getTransactionAdd(Model model) {
        model.addAttribute("categoryDTO", categoryDAO.getAllCategories());
        model.addAttribute("typeDTO", typeDAO.getAllTypeByLoggedUser(userService.getLoggedUser()));
        model.addAttribute("transactionDTO", new TransactionDTO());
        return "transactionAdd";
    }

    @PostMapping("/transaction/add")
    public String postTransactionAdd(@ModelAttribute("transactionDTO") TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        Optional<Type> type = typeDAO.getTypeById(transactionDTO.getTypeId());
        transaction.setType(type.get());
        transaction.setDate(transactionDTO.getDate());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setUser(userService.getLoggedUser());
        transactionDAO.addTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/transaction/delete/{id}")
    public String getTransactionDelete(@PathVariable("id") Integer id) {
        transactionDAO.deleteTransactionById(id);
        return "redirect:/transactions";

    }

    @GetMapping("/transaction/update/{id}")
    public String getTransactionUpdate(@PathVariable("id") Integer id, Model model) {
       Transaction transaction = transactionDAO.getTransactionById(id).get();
        if(transaction != null) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId(transaction.getId());
            transactionDTO.setTypeId(transaction.getType().getId());
            transactionDTO.setDate(transaction.getDate());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setUserId(userService.getLoggedUser().getId());
            model.addAttribute("categoryDTO", categoryDAO.getAllCategories());
            model.addAttribute("typeDTO", typeDAO.getAllTypeByLoggedUser(userService.getLoggedUser()));
            model.addAttribute("transactionDTO", transactionDTO);
            return "transactionAdd";
        }
        else
            return "404";
    }
}

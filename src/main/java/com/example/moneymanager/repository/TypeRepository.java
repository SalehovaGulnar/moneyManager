package com.example.moneymanager.repository;

import com.example.moneymanager.model.Type;
import com.example.moneymanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    List<Type> findAllByUser_Id(Integer id);

    List<Type> findAllByUser_IdIn(List<Integer> id);
}

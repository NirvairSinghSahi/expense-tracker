package com.example.expense.repository;

import com.example.expense.model.Expense;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    // get all expenses for logged-in user
    List<Expense> findByUserId(Long userId);

    // 🔥 FIXED filtering (user + category)
    List<Expense> findByUserIdAndCategory(Long userId, String category);
}
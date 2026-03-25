package com.example.expense.service;

import com.example.expense.model.Expense;
import com.example.expense.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    // get all for user
    public List<Expense> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    // save
    public void save(Expense expense) {
        repository.save(expense);
    }

    // delete
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // get one
    public Expense getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // 🔥 FIXED filter
    public List<Expense> getByUserAndCategory(Long userId, String category) {
        return repository.findByUserIdAndCategory(userId, category);
    }

    // total per user
    public double getTotalExpenses(Long userId) {
        return getByUser(userId).stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    public List<Expense> getAll() {
        return repo.findAll();
    }

    public Expense add(Expense expense) {
        return repo.save(expense);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Expense> filterByCategory(String category) {
        return repo.findByCategory(category);
    }

    public double calculateTotal() {
        return repo.findAll().stream().mapToDouble(Expense::getAmount).sum();
    }
}

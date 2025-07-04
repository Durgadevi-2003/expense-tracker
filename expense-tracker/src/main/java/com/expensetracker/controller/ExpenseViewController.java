package com.expensetracker.controller;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class ExpenseViewController {

    @Autowired
    private ExpenseService service;

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "category", required = false) String category) {
        List<Expense> expenses = (category != null && !category.isEmpty()) ?
                service.filterByCategory(category) : service.getAll();

        model.addAttribute("expenses", expenses);
        model.addAttribute("expense", new Expense());
        model.addAttribute("total", service.calculateTotal());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Expense expense) {
        service.add(expense);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}

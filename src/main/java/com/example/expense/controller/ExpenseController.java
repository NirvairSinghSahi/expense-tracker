package com.example.expense.controller;

import com.example.expense.model.Expense;
import com.example.expense.model.User;
import com.example.expense.service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    // 🏠 Home page (user-specific)
    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("expenses", service.getByUser(user.getId()));
        model.addAttribute("total", service.getTotalExpenses(user.getId()));
        model.addAttribute("expense", new Expense());

        return "index";
    }

    // ➕ Add
    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expense expense,
                             HttpSession session) {

        User user = (User) session.getAttribute("user");

        expense.setUserId(user.getId());

        service.save(expense);

        return "redirect:/";
    }

    // ❌ Delete
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

    // ✏️ Edit page
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("expense", service.getById(id));
        return "edit";
    }

    // 🔄 Update
    @PostMapping("/update")
    public String updateExpense(@ModelAttribute Expense expense,
                                HttpSession session) {

        User user = (User) session.getAttribute("user");

        // 🔥 ensure user stays same
        expense.setUserId(user.getId());

        service.save(expense);

        return "redirect:/";
    }

    // 🔍 FILTER (FIXED)
    @GetMapping("/category")
    public String filter(@RequestParam String category,
                         Model model,
                         HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("expenses",
                service.getByUserAndCategory(user.getId(), category));

        model.addAttribute("total",
                service.getTotalExpenses(user.getId()));

        model.addAttribute("expense", new Expense());

        return "index";
    }
}
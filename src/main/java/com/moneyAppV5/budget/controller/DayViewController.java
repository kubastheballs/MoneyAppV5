package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dayView/{day}")
public class DayViewController
{
    private final BudgetService budgetService;
    private final TransactionService transactionService;

    public DayViewController(BudgetService budgetService, TransactionService transactionService) {
        this.budgetService = budgetService;
        this.transactionService = transactionService;
    }

    @GetMapping
    String showDayView(Model model, @PathVariable String day)
    {
//        TODO

        return "dayView";
    }
}

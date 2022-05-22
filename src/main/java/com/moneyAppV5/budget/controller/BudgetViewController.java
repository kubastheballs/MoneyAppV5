package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/budgetView/{hash}")
public class BudgetViewController
{
    private final BudgetService service;

    public BudgetViewController(BudgetService service)
    {
        this.service = service;
    }

    @GetMapping()
    public String showBudgetView(Model model, @PathVariable Integer hash)
    {
        var budget = this.service.readBudgetByHash(hash);

        this.service.checkPositionsByBudget(budget);

        var result = this.service.readBudgetAsDto(budget);

        model.addAttribute("message", String.format("Budżet: %s/%s", result.getMonth(), result.getYear()));
        model.addAttribute("budget", result);

//TODO z obiektu
//        model.addAttribute("actualIncome", this.service.sumTransactionsByBudgetIdAndType(budget.getId(), Type.INCOME));
//        model.addAttribute("actualExpense", this.service.sumTransactionsByBudgetIdAndType(budget.getId(), Type.EXPENSE));
//
//        model.addAttribute("incomePositions", this.service.readPositionsDtoByBudgetAndType(budget, Type.INCOME));
//        model.addAttribute("expensePositions", this.service.readPositionsDtoByBudgetAndType(budget, Type.EXPENSE));
//        model.addAttribute("transactions", this.service.readTransactionsDtoByBudget(budget));

        return "budgetView";
    }
}

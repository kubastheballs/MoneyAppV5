package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/budgets")
public class BudgetsController
{
    BudgetService service;

    BudgetsController(BudgetService service)
    {
        this.service = service;
    }

    @GetMapping()
    String showBudgets(Model model)
    {
        model.addAttribute("budgetsList", null);

        return "budgets";
    }

    @ModelAttribute("budgetsList")
    List<BudgetDTO> getBudgetsListDto()
    {
        return this.service.readAllBudgetsDto();
    }

}

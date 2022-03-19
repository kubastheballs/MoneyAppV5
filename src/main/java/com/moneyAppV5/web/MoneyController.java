package com.moneyAppV5.web;

import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/moneys")
class MoneyController
{
    private final BudgetService budgetService;

    public MoneyController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    String showMoneyApp(Model model)
    {
        model.addAttribute("budget", new BudgetDTO());

        return "moneys";
    }

    @PostMapping()
    String addBudget(@ModelAttribute("budgetDto") @Valid BudgetDTO current, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");

            return "moneys";
        }

        var budget = this.budgetService.createBudget(current);

        this.budgetService.createPositionsListByBudget(budget);

        model.addAttribute("budget", new BudgetDTO());
        model.addAttribute("message", "Dodano budżet!");

//        return "redirect:/budgets/" + budget.getId();
        return "moneys";
    }


//    @GetMapping(path = "/categories")
//    String showCategories(Model model)
//    {
//        model.addAttribute("category", new CategoryDTO());
//        return "categories";
//    }
}

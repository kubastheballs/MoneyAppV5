package com.moneyAppV5.budget.controller;

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
import java.time.LocalDate;
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
        model.addAttribute("budget", new BudgetDTO());
        model.addAttribute("budgetsList", getBudgetsListDto());

        return "budgets";
    }

    @PostMapping()
    String addBudget(@ModelAttribute("budget") @Valid BudgetDTO current, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");

            return "budgets";
        }

        var result = this.service.createBudget(current);

        this.service.createPositionsListByBudget(result);
//TODO ustawiania dla nowegu budżetu miesiąca i roku na podstawie aktualnych w systemie
        model.addAttribute("budget", new BudgetDTO());
        model.addAttribute("month", LocalDate.now().getMonthValue());
        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("message", "Dodano budżet!");
        model.addAttribute("budgetsList", getBudgetsListDto());

        return "budgets";
    }

    @ModelAttribute("budgetsList")
    List<BudgetDTO> getBudgetsListDto()
    {
        return this.service.readAllBudgetsDto();
    }

//    @ModelAttribute("budgetId")
//    Integer getBudgetIdByMonthAndYear(Integer month, Integer year)
//    {
////        TODO jak wtłoczyć month i year do metdoy z html?
//        return this.service.readByMonthAndYear(month, year).getId();
//    }

}

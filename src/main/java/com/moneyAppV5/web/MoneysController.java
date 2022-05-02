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
import java.time.LocalDate;
import java.time.Month;

@Controller
@RequestMapping("/moneys")
class MoneysController
{
    private final BudgetService budgetService;

    public MoneysController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    String showMoneyApp(Model model)
    {
        model.addAttribute("hash", this.budgetService.readNewestBudgetHash());

        return "moneys";
    }
}

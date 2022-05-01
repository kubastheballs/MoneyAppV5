package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/positionView/{hash}")
public class PositionViewController
{
    private final BudgetService service;

    public PositionViewController(BudgetService service)
    {
        this.service = service;
    }

    @GetMapping
    String showPositionView(Model model, @PathVariable Integer hash)
    {
        var position = this.service.readPositionDtoByHash(hash);

        model.addAttribute("position", position);
        model.addAttribute("transactions", position.getTransactionsDto());

        return "positionView";
    }
}
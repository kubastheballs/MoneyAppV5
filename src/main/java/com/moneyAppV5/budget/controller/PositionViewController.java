package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

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
        var position = this.service.readPositionByHash(hash);
//        var result = new BudgetPositionDTO(position);
        var result = this.service.readBudgetPositionViewAsDto(position);
//        var budget = new BudgetDTO(this.service.readBudgetById(position.getBudget().getId()));

        model.addAttribute("position", result);
        model.addAttribute("budgetHash", result.getBudgetDto().getHash());
//        model.addAttribute("budget", budget);
//        model.addAttribute("dailySums", this.service.sumDailyTransactionsByPositionIdAndMonth(position.getId(), Month.of(budget.getMonth()).length(Year.isLeap(budget.getMonth()))));
//        model.addAttribute("monthLength", Month.of(budget.getMonth()).length(Year.isLeap(budget.getMonth())));
//        model.addAttribute("transactions", result.getTransactionsDto());
//
//        var month = LocalDate.now().getMonthValue();
//        var year = LocalDate.now().getYear();
//
//        model.addAttribute("actualMonthSum", this.service.sumTransactionsByPositionAndMonth(position, month, year));
////        TODO - patrz budgetService
//        model.addAttribute("actualQuarterSum", this.service.sumTransactionsByPositionAndQuarter(position, month, year));
//        model.addAttribute("actualYearSum", this.service.sumTransactionsByPositionAndYear(position, year));

        return "positionView";
    }
}

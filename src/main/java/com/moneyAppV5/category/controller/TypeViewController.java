package com.moneyAppV5.category.controller;

import com.moneyAppV5.budget.dto.BudgetStatsWrapperDTO;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.service.TransactionService;
import com.moneyAppV5.utils.UtilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/typeView/{type}")
public class TypeViewController
{
    private final CategoryService service;
    private final UtilService utilService;
    private final BudgetService budgetService;
    private final TransactionService transactionService;

    public TypeViewController(CategoryService service, UtilService utilService, BudgetService budgetService, TransactionService transactionService)
    {
        this.service = service;
        this.utilService = utilService;
        this.budgetService = budgetService;
        this.transactionService = transactionService;
    }

    @GetMapping
    String showTypeView(Model model, @PathVariable String type)
    {
        var typeDto = this.service.readTypeAsDto(type);

        model.addAttribute("typeDto", typeDto);

        //        TODO dopracować przypadek braku budżetu
        model.addAttribute("actualBudgets", this.budgetService.readActualBudgetsWrapper(this.utilService.getActualMonthValue(), this.utilService.getActualYear()));

        var transactions = this.transactionService.readTransactionsByTypeName(type);

        model.addAttribute("budgetStats", new BudgetStatsWrapperDTO(this.budgetService.readBudgetsWithMaxMinTransactionCountsByListAsDto(transactions),
                this.budgetService.readBudgetsWithMaxMinTransactionSumsByListAsDto(transactions)));

        return "typeView";
    }
}

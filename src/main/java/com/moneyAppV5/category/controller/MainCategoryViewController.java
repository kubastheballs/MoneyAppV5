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
@RequestMapping("/mainCategoryView/{hash}")
public class MainCategoryViewController
{
    private final CategoryService service;
    private final TransactionService transactionService;
    private final BudgetService budgetService;
    private final UtilService utilService;

    public MainCategoryViewController(CategoryService service, TransactionService transactionService, BudgetService budgetService,
                                      UtilService utilService)
    {
        this.service = service;
        this.transactionService = transactionService;
        this.budgetService = budgetService;
        this.utilService = utilService;
    }

    @GetMapping
    String showMainCategoryView(@PathVariable Integer hash, Model model)
    {
        var mainCat = this.service.readMainCategoryByHash(hash);
        var main = this.service.readMainCategoryAsDto(mainCat);

        model.addAttribute("mainCat", main);

//        TODO dopracować przypadek braku budżetu
        model.addAttribute("actualBudgets", this.budgetService.readActualBudgetsWrapper(this.utilService.getActualMonthValue(), this.utilService.getActualYear()));

        var transactions = this.transactionService.readTransactionsByMainCategoryId(mainCat.getId());

        model.addAttribute("budgetStats", new BudgetStatsWrapperDTO(this.budgetService.readBudgetsWithMaxMinTransactionCountsByListAsDto(transactions),
                this.budgetService.readBudgetsWithMaxMinTransactionSumsByListAsDto(transactions)));

        return "mainCategoryView";
    }
}

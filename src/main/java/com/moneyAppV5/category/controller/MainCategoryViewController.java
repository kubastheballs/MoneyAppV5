package com.moneyAppV5.category.controller;

import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.service.TransactionService;
import com.moneyAppV5.utils.UtilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

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
//        TODO
        var mainCat = this.service.readMainCategoryByHash(hash);
        var main = this.service.readMainCategoryAsDto(mainCat);

//        TODO dopracować przypadek braku budżetu
        model.addAttribute("actualBudget", this.budgetService.readBudgetByMonthAndYearAsDto(this.utilService.getActualMonthValue(), this.utilService.getActualYear()));
        model.addAttribute("actualBudgetMinusOne", this.budgetService.readBudgetByMonthAndYearAsDto(this.utilService.getActualMonthValue() - 1, this.utilService.getActualYear()));
        model.addAttribute("actualBudgetMinusTwo", this.budgetService.readBudgetByMonthAndYearAsDto(this.utilService.getActualMonthValue() - 2, this.utilService.getActualYear()));

        model.addAttribute("mainCat", main);

        var budgetSumsData = this.budgetService.readBudgetsWithMaxMinTransactionSumsByMainCategoryIdAsDto(mainCat.getId());

        model.addAttribute("highestSumBudget", budgetSumsData.getHighestSumBudget());
        model.addAttribute("highestSum", budgetSumsData.getHighestSum());
        model.addAttribute("lowestSumBudget", budgetSumsData.getLowestSumBudget());
        model.addAttribute("lowestSum", budgetSumsData.getLowestSum());

        var budgetCountsData = this.budgetService.readBudgetsWithMaxMinTransactionCountsByMainCategoryIdAsDto(mainCat.getId());

        model.addAttribute("highestCountBudget", budgetCountsData.getHighestCountBudget());
        model.addAttribute("highestCount", budgetCountsData.getHighestCount());
        model.addAttribute("lowestCountBudget", budgetCountsData.getLowestCountBudget());
        model.addAttribute("lowestCount", budgetCountsData.getLowestCount());

        return "mainCategoryView";
    }
}

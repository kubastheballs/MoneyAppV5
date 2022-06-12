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
@RequestMapping("subCategoryView/{hash}")
public class SubCategoryViewController
{
    private final CategoryService service;
    private final BudgetService budgetService;
    private final UtilService utilService;
    private final TransactionService transactionService;

    public SubCategoryViewController(CategoryService service, BudgetService budgetService, UtilService utilService, TransactionService transactionService)
    {
        this.service = service;
        this.budgetService = budgetService;
        this.utilService = utilService;
        this.transactionService = transactionService;
    }

    @GetMapping
    String showSubCategory(Model model, @PathVariable Integer hash)
    {
        var subCat = this.service.readSubCategoryBudgetByHash(hash);

        model.addAttribute("subCategory", this.service.readSubCategoryByHashAsDto(hash));

        //        TODO dopracować przypadek braku budżetu - póki co ogarnięte na poziomie html
        model.addAttribute("actualBudgets", this.budgetService.readActualBudgetsWrapper(this.utilService.getActualMonthValue(), this.utilService.getActualYear()));

        var transactions = this.transactionService.readTransactionsBySubCategoryId(subCat.getId());

        model.addAttribute("budgetStats", new BudgetStatsWrapperDTO(this.budgetService.readBudgetsWithMaxMinTransactionCountsByListAsDto(transactions),
                this.budgetService.readBudgetsWithMaxMinTransactionSumsByListAsDto(transactions)));

        return "subCategoryView";
    }
}

package com.moneyAppV5.category.controller;

import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.service.CategoryService;
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

    public SubCategoryViewController(CategoryService service, BudgetService budgetService, UtilService utilService)
    {
        this.service = service;
        this.budgetService = budgetService;
        this.utilService = utilService;
    }

    @GetMapping
    String showSubCategory(Model model, @PathVariable Integer hash)
    {
        var subCat = this.budgetService.readBudgetByHash(hash);

        model.addAttribute("subCategory", this.service.readSubCategoryByHashAsDto(hash));

        //        TODO dopracować przypadek braku budżetu
//        TODO może faktycznie warto to zrolować do jednej klasy?
        model.addAttribute("actualBudget", this.budgetService.readBudgetByMonthAndYearAsDto(this.utilService.getActualMonthValue(), this.utilService.getActualYear()));
        model.addAttribute("actualBudgetMinusOne", this.budgetService.readBudgetByMonthAndYearAsDto(this.utilService.getActualMonthValue() - 1, this.utilService.getActualYear()));
        model.addAttribute("actualBudgetMinusTwo", this.budgetService.readBudgetByMonthAndYearAsDto(this.utilService.getActualMonthValue() - 2, this.utilService.getActualYear()));

        var budgetSumsData = this.budgetService.readBudgetsWithMaxMinTransactionSumsByMainCategoryIdAsDto(subCat.getId());

        model.addAttribute("highestSumBudget", budgetSumsData.getHighestSumBudget());
        model.addAttribute("highestSum", budgetSumsData.getHighestSum());
        model.addAttribute("lowestSumBudget", budgetSumsData.getLowestSumBudget());
        model.addAttribute("lowestSum", budgetSumsData.getLowestSum());

        var budgetCountsData = this.budgetService.readBudgetsWithMaxMinTransactionCountsByMainCategoryIdAsDto(subCat.getId());

        model.addAttribute("highestCountBudget", budgetCountsData.getHighestCountBudget());
        model.addAttribute("highestCount", budgetCountsData.getHighestCount());
        model.addAttribute("lowestCountBudget", budgetCountsData.getLowestCountBudget());
        model.addAttribute("lowestCount", budgetCountsData.getLowestCount());

        return "subCategoryView";
    }
}

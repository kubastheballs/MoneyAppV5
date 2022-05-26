package com.moneyAppV5.account.controller;

import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.account.service.AccountService;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/accountView/{hash}")
public class AccountViewController
{
    AccountService service;
    BudgetService budgetService;

    public AccountViewController(AccountService service, BudgetService budgetService)
    {
        this.service = service;
        this.budgetService = budgetService;
    }

    @GetMapping()
    public String showBudgetView(Model model, @PathVariable Integer hash)
    {
        var account = this.service.readAccountByHash(hash);
        var result = this.service.readAccountAsDto(account);

        model.addAttribute("message", String.format("Konto: %s", result.getName()));
        model.addAttribute("account", result);

        var budgetsSumsData = this.budgetService.readBudgetsWithMaxMinTransactionSumsByAccountIdAsDto(account.getId());

        model.addAttribute("highestSumBudget", budgetsSumsData.getHighestSumBudget());
        model.addAttribute("highestSum", budgetsSumsData.getHighestSum());
        model.addAttribute("lowestSumBudget", budgetsSumsData.getLowestSumBudget());
        model.addAttribute("lowestSum", budgetsSumsData.getLowestSum());

        var budgetsCountsData = this.budgetService.readBudgetsWithMaxMinTransactionCountsByAccountIdAsDto(account.getId());

        model.addAttribute("highestCountBudget", budgetsCountsData.getHighestCountBudget());
        model.addAttribute("highestCount", budgetsCountsData.getHighestCount());
        model.addAttribute("lowestCountBudget", budgetsCountsData.getLowestCountBudget());
        model.addAttribute("lowestCount", budgetsCountsData.getLowestCount());

        return "accountView";
    }

}

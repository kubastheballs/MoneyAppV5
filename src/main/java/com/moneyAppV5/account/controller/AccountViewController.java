package com.moneyAppV5.account.controller;

import com.moneyAppV5.account.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accountView/{id}")
public class AccountViewController
{
    AccountService service;

    public AccountViewController(AccountService service)
    {
        this.service = service;
    }

    @GetMapping()
    public String showBudgetView(Model model, @PathVariable Integer id)
    {
        var result = this.service.readAccountDtoById(id);

        model.addAttribute("message", String.format("Konto: %s", result.getName()));
        model.addAttribute("account", result);
        model.addAttribute("id", id);
//        TODO lista transakcji z danego konta - raczej wszystkie i ew sortowanie po typie
//        model.addAttribute("incomePositions", result.getIncomes());
//        model.addAttribute("expensePositions", result.getExpenses());

        return "accountView";
    }
}

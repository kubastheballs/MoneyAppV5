package com.moneyAppV5.account.controller;

import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.account.service.AccountService;
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

    public AccountViewController(AccountService service)
    {
        this.service = service;
    }

    @GetMapping()
    public String showBudgetView(Model model, @PathVariable Integer hash)
    {
        var account = this.service.readAccountByHash(hash);
        var result = this.service.readAccountAsDto(account);

        model.addAttribute("message", String.format("Konto: %s", result.getName()));
        model.addAttribute("account", result);

        return "accountView";
    }

}

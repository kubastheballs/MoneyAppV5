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
@RequestMapping("/accountView/")
//@RequestMapping("/accountView/{id}")
public class AccountViewController
{
    AccountService service;

    public AccountViewController(AccountService service)
    {
        this.service = service;
    }

//    @GetMapping("{id}")
////    @GetMapping()
//    public String showBudgetView(Model model, @PathVariable Integer id)
//    {
//        var result = this.service.readAccountDtoById(id);
//
//        model.addAttribute("message", String.format("Konto: %s", result.getName()));
//        model.addAttribute("account", result);
//        model.addAttribute("id", id);
////        TODO lista transakcji z danego konta - raczej wszystkie razem i ew sortowanie po typie
////        model.addAttribute("incomePositions", result.getIncomes());
////        model.addAttribute("expensePositions", result.getExpenses());
//
//        return "accountView";
//    }

    @GetMapping("{hash}")
//    @GetMapping()
    public String showBudgetView(Model model, @PathVariable Integer hash)
    {
        var account = this.service.readAccountByHash(hash);
        var result = new AccountDTO(account);

        model.addAttribute("message", String.format("Konto: %s", result.getName()));
        model.addAttribute("account", result);
//        model.addAttribute("id", id);
//        TODO lista transakcji z danego konta - raczej wszystkie razem i ew sortowanie po typie
//        model.addAttribute("incomePositions", result.getIncomes());
//        model.addAttribute("expensePositions", result.getExpenses());
//        TODO metody dla modelu na dochody i wydatki z bieżącego miesiąca i ogółem
        Integer month = LocalDate.now().getMonthValue();
        Integer year = LocalDate.now().getYear();

        model.addAttribute("actualMonthIncome", this.service.sumActualMonthTransactionsByType(account, month, year, Type.INCOME));
        model.addAttribute("actualMonthExpense", this.service.sumActualMonthTransactionsByType(account, month, year, Type.EXPENSE));
        model.addAttribute("actualMonthBalance", this.service.balanceActualMonthTransactions(account, month, year));

        model.addAttribute("overallIncome", this.service.sumOverallTransactionsByType(account, Type.INCOME));
        model.addAttribute("overallExpense", this.service.sumOverallTransactionsByType(account, Type.EXPENSE));
        model.addAttribute("overallBalance", this.service.balanceOverallTransactions(account));

        return "accountView";
    }

}

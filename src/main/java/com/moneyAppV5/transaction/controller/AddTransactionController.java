package com.moneyAppV5.transaction.controller;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.service.AccountService;
import com.moneyAppV5.budget.controller.BudgetViewController;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/budgetView/{hash}/addTransaction")
//@RequestMapping("/budgetView/{id}/addTransaction")
public class AddTransactionController
{
    TransactionService service;
    CategoryService categoryService;
    AccountService accountService;
    BudgetService budgetService;
    BudgetViewController viewController;

    public AddTransactionController(TransactionService service, CategoryService categoryService, AccountService accountService,
                                    BudgetService budgetService, BudgetViewController viewController) {
        this.service = service;
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.budgetService = budgetService;
        this.viewController = viewController;
    }

    @GetMapping()
    String showAddTransaction(Model model, @PathVariable Integer hash)
    {
        var dto = new TransactionDTO();
        var budgetDto = new BudgetDTO(this.budgetService.readBudgetByHash(hash));

        dto.setMonth(budgetDto.getMonth());
        dto.setYear(budgetDto.getYear());

        model.addAttribute("transaction", dto);
        model.addAttribute("budgetHash", hash);
        model.addAttribute("budget", budgetDto.toBudget());

        return "addTransaction";
    }

    @PostMapping()
    String addTransaction(@ModelAttribute("transaction") @Valid TransactionDTO current, BindingResult bindingResult, Model model, @PathVariable Integer hash)
    {

        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");

            return "addTransaction";
        }

        var dto = new TransactionDTO();
        var budget = this.budgetService.readBudgetByHash(hash);

        dto.setMonth(budget.getMonth());
        dto.setYear(budget.getYear());

        var position = this.budgetService.readPositionByBudgetHashAndCategory(hash, current.getCategory());

        var transaction =  this.service.createTransaction(current);

        transaction.setBudget(budget);
        transaction.setBudgetPosition(position);

        this.accountService.changeBalance(transaction.getAccount().getId(), transaction.getAmount());

        model.addAttribute("transaction", dto);
        model.addAttribute("budgetHash", hash);
        model.addAttribute("accountsList", getAccounts());
        model.addAttribute("categoriesList", getCategories());
        model.addAttribute("isPaidList", getIsPaidList());
        model.addAttribute("forWhomList", getForWhomList());
        model.addAttribute("message", "Dodano transakcję!");

        return "addTransaction";
    }

//    @GetMapping(value ="redirect:/budgetView/{id}", params="return")
//    String returnToBudgetView(@PathVariable Integer id, Model model)
//    {
//        model.addAttribute("budgetId", id);
//
////        return String.format("redirect:/budgetView/%s", id);
////        return String.format("redirect:/%s", this.viewController.showBudgetView(model, id));
//        return "budgetView";
//    }

    @ModelAttribute("accountsList")
    List<Account> getAccounts()
    {
        return this.accountService.readAllAccounts();
    }

    @ModelAttribute("categoriesList")
    List<Category> getCategories()
    {
//        TODO jak tu przekazać type z selecta w html żeby sortowało kategorie?
//        return this.categoryService.readCategoriesByType(type);
        return this.categoryService.readAllCategories();
    }

    @ModelAttribute("isPaidList")
    List<Payee> getIsPaidList()
    {
        return this.service.readPayeesByRole(Role.IS_PAID);
    }

    @ModelAttribute("forWhomList")
    List<Payee> getForWhomList()
    {
        return this.service.readPayeesByRole(Role.IS_FOR);
    }
}

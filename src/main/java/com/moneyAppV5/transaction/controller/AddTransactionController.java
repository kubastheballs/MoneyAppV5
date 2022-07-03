package com.moneyAppV5.transaction.controller;

import com.moneyAppV5.account.service.AccountService;
import com.moneyAppV5.bill.dto.BillWriteModel;
import com.moneyAppV5.bill.service.BillService;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.Role;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/budgetView/{hash}/addTransaction")
public class AddTransactionController
{
    private final BillService billService;
    private final TransactionService transactionService;
    private final CategoryService categoryService;
    private final AccountService accountService;
    private final BudgetService budgetService;

    public AddTransactionController(TransactionService transactionService, CategoryService categoryService, AccountService accountService,
                                    BudgetService budgetService, BillService billService) {
        this.billService = billService;
        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.budgetService = budgetService;
    }

    @GetMapping()
    String showAddTransaction(Model model, @PathVariable Integer hash)
    {
        var dto = new BillWriteModel();
        var budgetDto = new BudgetDTO(this.budgetService.readBudgetByHash(hash));

        model.addAttribute("bill", dto);
        model.addAttribute("budgetHash", hash);
        model.addAttribute("budget", budgetDto.toBudget());

        return "addTransactions";
    }

    @PostMapping()
    String addBill(@ModelAttribute("bill") @Valid BillWriteModel current, BindingResult bindingResult, Model model, @PathVariable Integer hash)
    {

        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");

            return "addTransactions";
        }

//TODO tu będzie lista pozycji na podstawie transakcji w danym rachunku

        var billDto = current.toDto();

        billDto.setBudget(this.budgetService.readBudgetByHash(hash).toDto());



//        TODO
//        var positions = this.budgetService.readPositionsByBudgetHashAndCategories(hash,
//                this.transactionService.readCategoriesIdsByBillId(bill.getId()));

        var bill =  this.billService.createBill(billDto);
//        hidden?
//        bill.setBudgetPositions(positions);

        this.accountService.changeBalanceByAccountId(bill.getAccount().getId(), current.sumTransactions());

        model.addAttribute("bill", new BillWriteModel());
        model.addAttribute("budgetHash", hash);
        model.addAttribute("accountsList", this.accountService.readAllAccounts());
        model.addAttribute("categoriesList", this.categoryService.readAllCategories());
        model.addAttribute("payeesList", this.transactionService.readPayeesByRole(Role.PAYEE));
        model.addAttribute("gainersList", this.transactionService.readPayeesByRole(Role.GAINER));
        model.addAttribute("message", "Dodano rachunek!");

        return "addTransactions";
    }

    @PostMapping(params = "addTransaction")
    String addTransactionToBill(@ModelAttribute("bill") BillWriteModel current, Model model, @PathVariable Integer hash)
    {
        current.getTransactions().add(new Transaction());

        model.addAttribute("budgetHash", hash);

        return "addTransactions";
    }
}
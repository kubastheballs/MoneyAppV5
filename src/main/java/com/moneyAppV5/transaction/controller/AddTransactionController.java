package com.moneyAppV5.transaction.controller;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.service.AccountService;
import com.moneyAppV5.bill.dto.BillWriteModel;
import com.moneyAppV5.bill.service.BillService;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
//        model.addAttribute("budgetHash", hash);
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

//TODO tu będzie lista pozycji na podstawie transakcji w danym rachunku - chyba że pozycje dopiero przy otwieraniu budżetu
//        TODO
//        var positions = this.budgetService.readPositionsByBudgetHashAndCategories(hash,
//                this.transactionService.readCategoriesIdsByBillId(bill.getId()));

        current.setBudget(this.budgetService.readBudgetByHash(hash));

        this.billService.createBill(current);

        this.accountService.changeBalanceByAccountId(current.getAccount().getId(), current.sumTransactions());

        model.addAttribute("bill", new BillWriteModel());
//        model.addAttribute("budgetHash", hash);
        model.addAttribute("message", "Dodano rachunek!");

        return "addTransactions";
    }

    @PostMapping(params = "addTransaction")
    String addTransactionToBill(@ModelAttribute("bill") BillWriteModel current, Model model, @PathVariable Integer hash)
    {
        current.getTransactions().add(new Transaction());

        model.addAttribute("bill", current);
//        model.addAttribute("budgetHash", hash);

        return "addTransactions";
    }

    @ModelAttribute("budgetHash")
    Integer getBudgetHash(@PathVariable Integer hash)
    {
        return hash;
    }

   @ModelAttribute("accountsList")
    List<Account> getAccounts()
   {
       return this.accountService.readAllAccounts();
   }

   @ModelAttribute("categoriesList")
    List<Category> getCategories()
   {
       return this.categoryService.readAllCategories();
   }

   @ModelAttribute("payeesList")
    List<Payee> getPayees()
   {
       return this.transactionService.readPayeesByRole(Role.PAYEE);
   }

    @ModelAttribute("gainersList")
    List<Payee> getGainers()
    {
        return this.transactionService.readPayeesByRole(Role.GAINER);
    }
}
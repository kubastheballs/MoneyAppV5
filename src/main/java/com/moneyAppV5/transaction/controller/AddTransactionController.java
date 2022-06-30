package com.moneyAppV5.transaction.controller;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.service.AccountService;
import com.moneyAppV5.bill.dto.BillDTO;
import com.moneyAppV5.bill.service.BillService;
import com.moneyAppV5.budget.controller.BudgetViewController;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;
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
    private BillService billService;
    private TransactionService transactionService;
    private CategoryService categoryService;
    private AccountService accountService;
    private BudgetService budgetService;
    private BudgetViewController viewController;

    public AddTransactionController(TransactionService transactionService, CategoryService categoryService, AccountService accountService,
                                    BudgetService budgetService, BudgetViewController viewController, BillService billService) {
        this.billService = billService;
        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.budgetService = budgetService;
        this.viewController = viewController;
    }

    @GetMapping()
    String showAddTransaction(Model model, @PathVariable Integer hash)
    {
        var dto = new BillDTO();
        var budgetDto = new BudgetDTO(this.budgetService.readBudgetByHash(hash));

//        dto.setMonth(budgetDto.getMonth());
//        dto.setYear(budgetDto.getYear());

        model.addAttribute("bill", dto);
        model.addAttribute("budgetHash", hash);
        model.addAttribute("budget", budgetDto.toBudget());

        return "addTransactions";
    }

    @PostMapping()
    String addBill(@ModelAttribute("bill") @Valid BillDTO current, BindingResult bindingResult, Model model, @PathVariable Integer hash)
    {

        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");

            return "addTransactions";
        }

        var budget = this.budgetService.readBudgetByHash(hash);
//TODO tu będzie lista pozycji na podstawie transakcji w danym rachunku

        var bill =  this.billService.createBill(current);

        var positions = this.budgetService.readPositionsByBudgetHashAndCategories(hash,
                this.transactionService.readCategoriesIdsByBillId(bill.getId()));

        bill.setBudget(budget);
//        hidden?
//        bill.setBudgetPositions(positions);

        this.accountService.changeBalanceByAccountId(bill.getAccount().getId(), current.sumTransactions());

        model.addAttribute("bill", new BillDTO());
        model.addAttribute("budgetHash", hash);
        model.addAttribute("accountsList", getAccounts());
        model.addAttribute("categoriesList", getCategories());
        model.addAttribute("payeesList", getIsPaidList());
        model.addAttribute("gainersList", getForWhomList());
        model.addAttribute("message", "Dodano rachunek!");

        return "addTransactions";
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
        return this.transactionService.readPayeesByRole(Role.IS_PAID);
    }

    @ModelAttribute("forWhomList")
    List<Payee> getForWhomList()
    {
        return this.transactionService.readPayeesByRole(Role.IS_FOR);
    }
}

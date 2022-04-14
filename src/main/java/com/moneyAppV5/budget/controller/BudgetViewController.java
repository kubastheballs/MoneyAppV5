package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class BudgetViewController
{
    private final BudgetService service;

    public BudgetViewController(BudgetService service)
    {
        this.service = service;
    }

//    raczej tylko gwoli testów tymczasowo
@GetMapping("/budgetView/{month}{year}")
public String showBudgetView(Model model, @PathVariable Integer month, @PathVariable Integer year)
{
    var result = this.service.readByMonthAndYear(month, year);

    model.addAttribute("message", String.format("Budżet: %s/%s", result.getMonth(), result.getYear()));
    model.addAttribute("budget", result);
    model.addAttribute("id", result.getId());
    model.addAttribute("incomePositions", result.getIncomes());
    model.addAttribute("expensePositions", result.getExpenses());

    return "budgetView";
}
//tu jest docelowy
    @GetMapping("/budgetView/{id}")
    public String showBudgetView(Model model, @PathVariable Integer id)
    {
        var result = this.service.readBudgetDtoById(id);

        model.addAttribute("message", String.format("Budżet: %s/%s", result.getMonth(), result.getYear()));
        model.addAttribute("budget", result);
        model.addAttribute("id", id);
        model.addAttribute("incomePositions", result.getIncomes());
        model.addAttribute("expensePositions", result.getExpenses());

        return "budgetView";
    }

//    @GetMapping(value ="redirect:/addTransaction", params="addT")
//    String goToAddTransaction(@PathVariable Integer id, Model model)
//    {
//        System.out.println("~~~~");
//        System.out.println(id);
//        model.addAttribute("budgetId", id);
//
//        return "addTransaction";
//    }

//    @ModelAttribute("expenseSubPositions")
//    List<BudgetPosition> getExpenseSubPositions()
//    {
//        return this.service.
//    }

//    @GetMapping("/{id}/addTransaction")
//    String showTransactions(Model model, @PathVariable Integer id)
//    {
//        var dto = new TransactionDTO();
//        var budgetDto = this.service.readBudgetDtoById(id);
//
//        dto.setMonth(budgetDto.getMonth());
//        dto.setYear(budgetDto.getYear());
//        dto.setBudget(budgetDto.toBudget());
//
//        model.addAttribute("transaction", dto);
//
//        return "transactions";
//    }

//    @PostMapping("/{id}/addTransaction")
//    String addTransaction(Model model, @PathVariable Integer id)
//    {
//        var dto = new TransactionDTO();
//        var budgetDto = this.service.readBudgetDtoById(id);
//
//        dto.setMonth(budgetDto.getMonth());
//        dto.setYear(budgetDto.getYear());
//
//        model.addAttribute("transaction", dto);
//        model.addAttribute("transactions", getTransactionsDto());
//        model.addAttribute("accountsList", getAccounts());
////        model.addAttribute("expensesList", getExpenseCategories());
////        model.addAttribute("incomesList", getIncomeCategories());
//        model.addAttribute("categoriesList", getCategories());
////        model.addAttribute("interialsList", getCategories());
//        model.addAttribute("payeesList", getPayees());
//        model.addAttribute("gainersList", getGainers());
//
//        return "transactions";
//    }

//    @PostMapping
//    String budgets()
//    {
//        return "budgetView";
//    }

//    @ModelAttribute("transactions")
//    List<TransactionDTO> getTransactionsDto()
//    {
//        return this.transactionService.readAllTransactionsDTO();
//    }
//
//    @ModelAttribute("accountsList")
//    List<Account> getAccounts()
//    {
//        return this.accountService.readAllAccounts();
//    }
//
//    @ModelAttribute("expensesList")
//    List<Category> getExpenseCategories()
//    {
//        return this.categoryService.readExpenseCategories();
//    }
//
//    @ModelAttribute("incomesList")
//    List<Category> getIncomeCategories()
//    {
//        return this.categoryService.readIncomeCategories();
//    }
//
//    @ModelAttribute("categoriesList")
//    List<Category> getCategories()
//    {
////        TODO jak tu przekazać type z selecta w html żeby sortowało kategorie?
////        return this.categoryService.readCategoriesByType(type);
//        return this.categoryService.readAllCategories();
//    }
//
//    @ModelAttribute("payeesList")
//    List<Payee> getPayees()
//    {
//        return this.transactionService.readAllPayees();
//    }
//
//    @ModelAttribute("gainersList")
//    List<Gainer> getGainers()
//    {
//        return this.transactionService.readAllGainers();
//    }


















//TODO w sumie chyba jest prościej jednak zrobić te pola w budżecie

//    @ModelAttribute("plannedIncomes")
//    Double getPlannedIncomes()
//    {
////        TODO wyciąganie planowanych dochodów
//        return null;
//    }
//
//    @ModelAttribute("actualIncomes")
//    Double getActualIncomes()
//    {
////        TODO wyciąganie rzeczywistych dochodów
//        return null;
//    }
//
//    @ModelAttribute("incomesBalance")
//    Double getIncomesBalance()
//    {
////        TODO wyciąganie różnicy między dochodem planowanym a rzeczyistym
//        return null;
//    }
//
//    @ModelAttribute("plannedExpenses")
//    Double getPlannedExpenses()
//    {
////        TODO wyciąganie planowanych wydatków
//        return null;
//    }
//
//    @ModelAttribute("actualExpenses")
//    Double getActualExpenses()
//    {
////        TODO wyciąganie rzeczywistych wydatków
//        return null;
//    }
//
//    @ModelAttribute("expensesBalance")
//    Double getExpensesBalance()
//    {
////        TODO wyciąganie różnicy między wydatkami planowanym a rzeczyistym
//        return null;
//    }
}

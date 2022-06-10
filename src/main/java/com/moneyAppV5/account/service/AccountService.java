package com.moneyAppV5.account.service;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.account.dto.AccountTransactionsSumDataDTO;
import com.moneyAppV5.account.repository.AccountRepository;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import com.moneyAppV5.transaction.service.TransactionService;
import com.moneyAppV5.utils.UtilService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService
{
    private final AccountRepository repository;
    private final TransactionService transactionService;
    private final BudgetService budgetService;
    private final UtilService utilService;

    public AccountService(AccountRepository repository, TransactionService transactionService, BudgetService budgetService, UtilService utilService)
    {
        this.repository = repository;
        this.transactionService = transactionService;
        this.budgetService = budgetService;
        this.utilService = utilService;
    }

    public Optional<Account> readAccountById(int id) {
        return this.repository.findById(id);
    }

    public boolean existsById(int id) {
        return this.repository.existsById(id);
    }

    public List<Account> readAllAccounts() {
        return this.repository.findAll();
    }

    public Account createAccount(final AccountDTO toSave) {
        return this.repository.save(toSave.toAccount());
    }

    public double getActualBalanceByAccount(Account account) {
        return this.repository.getAccountActualBalance(account);
    }

    public List<AccountDTO> readAllAccountsDTO() {
        List<AccountDTO> dtos = new ArrayList<>();

        for (Account acc : readAllAccounts())
            dtos.add(new AccountDTO(acc));

        return dtos;
    }

    public void changeBalanceByAccountId(Integer id, double amount, Type type)
    {
        if (type.equals(Type.EXPENSE))
            amount = -amount;

        this.repository.changeBalance(id, amount);
    }

    public AccountDTO readAccountDtoById(Integer id) {
//        TODO czy w orElseThrow należy wrzucić jakiś błąd? - sprawdzić jak jest w todo-app
        return new AccountDTO(this.repository.findById(id).orElseThrow());
    }

    public Account readAccountByName(String name) {
        return this.repository.findByName(name);
    }

    public Account readAccountByHash(Integer hash) {
        return this.repository.findByHash(hash).orElseThrow();
    }

    public boolean existsByName(String name) {
        return this.repository.existsByName(name);
    }

    public double sumTransactionsByTypeAndMonth(Account a, int month, int year, Type type) {

        var date = this.utilService.checkMonthValue(month, year);

        return this.transactionService.sumTransactionsByAccountAndMonthAndType(a, date[0], date[1], type);
    }

    public double sumOverallTransactionsByType(Account a, Type type) {
        return this.transactionService.sumOverallTransactionsByAccountAndType(a, type);
    }

    public double balanceTransactionsByMonth(Account account, int month, int year) {
      var date = this.utilService.checkMonthValue(month, year);

        return sumTransactionsByTypeAndMonth(account, date[0], date[1], Type.INCOME) - sumTransactionsByTypeAndMonth(account, date[0], date[1], Type.EXPENSE);
    }

    public double balanceOverallTransactions(Account account) {
        return sumOverallTransactionsByType(account, Type.INCOME) - sumOverallTransactionsByType(account, Type.EXPENSE);
    }



    public AccountDTO readAccountAsDto(Account a)
    {
        var account = new AccountDTO(a);

        account.setTransactions(this.transactionService.readTransactionsByAccountIdAsDto(a.getId()));

        var income = Type.INCOME;
        var expense = Type.EXPENSE;

        var month = LocalDate.now().getMonthValue();
        var year = LocalDate.now().getYear();

        account.setActualMonthBudget(this.budgetService.readBudgetOnlyWithActualByAccountIdAndMonthAsDto(this.utilService.checkMonthValue(month, year), a.getId()));
        account.setActualMonthMinusOneBudget(this.budgetService.readBudgetOnlyWithActualByAccountIdAndMonthAsDto(this.utilService.checkMonthValue(month - 1, year), a.getId()));
        account.setActualMonthMinusTwoBudget(this.budgetService.readBudgetOnlyWithActualByAccountIdAndMonthAsDto(this.utilService.checkMonthValue(month - 2, year), a.getId()));

        account.setActualYearIncome(this.utilService.sumByListAndTypeAndYear(account.getTransactions(), income, year));
        account.setActualYearExpense(this.utilService.sumByListAndTypeAndYear(account.getTransactions(), expense, year));
        account.setActualYearBalance(account.getActualYearIncome() - account.getActualYearExpense());

        account.setOverallIncome(this.utilService.sumByListAndType(account.getTransactions(), income));
        account.setOverallExpense(this.utilService.sumByListAndType(account.getTransactions(), expense));
        account.setOverallBalance(account.getOverallIncome() - account.getOverallExpense());

        return account;
    }


    public double sumAllAccountsBalances()
    {
       return this.repository.sumAllAccountsBalances().orElse(0.0);
    }
}

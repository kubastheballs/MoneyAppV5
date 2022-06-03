package com.moneyAppV5.account.service;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.account.dto.AccountTransactionsSumDataDTO;
import com.moneyAppV5.account.repository.AccountRepository;
import com.moneyAppV5.budget.service.BudgetService;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository repository;
    TransactionService transactionService;
    BudgetService budgetService;

    public AccountService(AccountRepository repository, TransactionService transactionService, BudgetService budgetService)
    {
        this.repository = repository;
        this.transactionService = transactionService;
        this.budgetService = budgetService;
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
        switch (month) {
            case 0 -> {
                month = 12;
                year = year - 1;
            }
            case -1 -> {
                month = 11;
                year = year - 1;
            }
        }

        return this.transactionService.sumTransactionsByAccountAndMonthAndType(a, month, year, type);
    }

    public double sumOverallTransactionsByType(Account a, Type type) {
        return this.transactionService.sumOverallTransactionsByAccountAndType(a, type);
    }

    public double balanceTransactionsByMonth(Account account, int month, int year) {
        switch (month) {
            case 0 -> {
                month = 12;
                year = year - 1;
            }
            case -1 -> {
                month = 11;
                year = year - 1;
            }
        }

        return sumTransactionsByTypeAndMonth(account, month, year, Type.INCOME) - sumTransactionsByTypeAndMonth(account, month, year, Type.EXPENSE);
    }

    public double balanceOverallTransactions(Account account) {
        return sumOverallTransactionsByType(account, Type.INCOME) - sumOverallTransactionsByType(account, Type.EXPENSE);
    }

    private int[] checkMonthValue(int month, int year) {
        switch (month) {
            case 0 -> {
                month = 12;
                year = year - 1;
            }
            case -1 -> {
                month = 11;
                year = year - 1;
            }


        }

        return new int[]{month, year};
    }

    public AccountDTO readAccountAsDto(Account a)
    {
        var account = new AccountDTO(a);

        account.setTransactions(this.transactionService.readTransactionsByAccountIdAsDto(a.getId()));

        var income = Type.INCOME;
        var expense = Type.EXPENSE;

        var month = LocalDate.now().getMonthValue();
        var year = LocalDate.now().getYear();

        account.setActualMonthBudget(this.budgetService.readBudgetOnlyWithActualByAccountIdAndMonthAsDto(checkMonthValue(month, year), a.getId()));
        account.setActualMonthMinusOneBudget(this.budgetService.readBudgetOnlyWithActualByAccountIdAndMonthAsDto(checkMonthValue(month - 1, year), a.getId()));
        account.setActualMonthMinusTwoBudget(this.budgetService.readBudgetOnlyWithActualByAccountIdAndMonthAsDto(checkMonthValue(month - 2, year), a.getId()));

        account.setActualYearIncome(sumByListAndTypeAndYear(account.getTransactions(), income, year));
        account.setActualYearExpense(sumByListAndTypeAndYear(account.getTransactions(), expense, year));
        account.setActualYearBalance(account.getActualYearIncome() - account.getActualYearExpense());

        account.setOverallIncome(sumByListAndType(account.getTransactions(), income));
        account.setOverallExpense(sumByListAndType(account.getTransactions(), expense));
        account.setOverallBalance(account.getOverallIncome() - account.getOverallExpense());

        return account;
    }

    private double sumByListAndTypeAndMonth(List<TransactionDTO> transactions, Type type, int month, int year)
    {
        switch (month) {
            case 0 -> {
                month = 12;
                year = year - 1;
            }
            case -1 -> {
                month = 11;
                year = year - 1;
            }
        }

        double sum = 0;

        for (TransactionDTO t : transactions)
            if ((t.getCategory().getType()).equals(type) && t.getMonth() == month && t.getYear() == year)
                sum += t.getAmount();

            return sum;
    }

    private double sumByListAndTypeAndYear(List<TransactionDTO> transactions, Type type, int year)
    {
        double sum = 0;

        for (TransactionDTO t : transactions)
            if ((t.getCategory().getType()).equals(type) && t.getYear() == year)
                sum += t.getAmount();

        return sum;
    }

    private double sumByListAndType(List<TransactionDTO> transactions, Type type)
    {
        double sum = 0;

        for (TransactionDTO t : transactions)
            if ((t.getCategory().getType()).equals(type))
                sum += t.getAmount();

        return sum;
    }
}

package com.moneyAppV5.transaction.service;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.GainerDTO;
import com.moneyAppV5.transaction.dto.PayeeDTO;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import com.moneyAppV5.transaction.repository.GainerRepository;
import com.moneyAppV5.transaction.repository.PayeeRepository;
import com.moneyAppV5.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService
{
    private final TransactionRepository repository;
    private final PayeeRepository payeeRepository;

    TransactionService(TransactionRepository repository, PayeeRepository payeeRepository)
    {
        this.repository = repository;
        this.payeeRepository = payeeRepository;
    }

    public boolean existsById(int id)
    {
        return this.repository.existsById(id);
    }

    public Transaction createTransaction(final TransactionDTO toSave)
    {
        return this.repository.save(toSave.toTransaction());
    }

    public Payee addPayee(final String name, final Role role)
    {
        return this.payeeRepository.findByName(name).orElse(createPayee(new PayeeDTO(name, role)));
    }

    public Payee createPayee(final PayeeDTO toSave)
    {
        return this.payeeRepository.save(toSave.toPayee());
    }

    public List<Transaction> readAllTransactions()
    {
        return this.repository.findAll();
    }

    public List<Payee> readAllPayees()
    {
        return this.payeeRepository.findAll();
    }

    public Optional<Transaction> readTransactionById(int id)
    {
        return this.repository.findById(id);
    }

    public Optional<Payee> readPayeeById(int id)
    {
        return this.payeeRepository.findById(id);
    }

    public List<TransactionDTO> readAllTransactionsDTO()
    {
        return readAllTransactions().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public List<PayeeDTO> readAllPayeesDto()
    {
        return readAllPayees().stream().map(PayeeDTO::new).collect(Collectors.toList());
    }

    public List<Transaction> readTransactionsByPositionId(Integer id)
    {
        return this.repository.findTransactionsByPositionId(id);
    }

    public List<Transaction> readTransactionsByMonthAndYear(int month, int year)
    {
        return this.repository.findTransactionsByMonthAndYear(month,year);
    }

    public List<Transaction> readTransactionsByBudgetId(int id)
    {
        return this.repository.findTransactionsByBudgetId(id);
    }

    public void updateBudgetDataInTransaction(int id, BudgetPosition pos)
    {
        this.repository.updateBudgetDetailsInTransaction(id, pos.getId(), pos.getBudget().getId());
    }

    public List<Payee> readPayeesByRole(Role role)
    {
        return this.payeeRepository.findPayeesByRole(role.name());
    }

    public double readActualExpensesByMainCategoryAndBudgetId(MainCategory main, int budgetId)
    {
        return this.repository.sumActualExpensesByMainCategoryIdAndBudgetId(main.getId(), budgetId);
    }
//TODO czy to jest potrzebne? wystarczy dać new PayeeDTO w miejscu docelowym - okaże się
    public PayeeDTO readPayeeDtoByHash(Integer hash) {
        return new PayeeDTO(readPayeeByHash(hash));
    }

    public Payee readPayeeByHash(Integer hash)
    {
        return this.payeeRepository.findByHash(hash);
    }

    public List<TransactionDTO> readTransactionsDtoByPayeeId(Integer id)
    {
        return this.repository.findByPayeeId(id).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public double sumTransactionsByAccountAndMonthAndType(Account a, Integer m, Integer y, Type type)
    {
        return this.repository.sumTransactionsByAccountIdAndMonthAndType(a.getId(), m, y, type.name()).orElse(0.0);
    }

    public double sumOverallTransactionsByAccountAndType(Account a, Type type)
    {
        return this.repository.sumTransactionsByAccountIdAndType(a.getId(), type.name()).orElse(0.0);
    }

    public double sumActualMonthTransactionsByCategory(Category category, Integer month, Integer year)
    {
        return this.repository.sumActualMonthTransactionsByCategoryId(category.getId(), month, year).orElse(0.0);
    }

    public double sumOverallTransactionsByCategory(Category category)
    {
        return this.repository.sumOverallTransactionsByCategoryId(category.getId()).orElse(0.0);
    }

    public double sumTransactionsByBudgetPosition(BudgetPosition p) {
        return this.repository.sumTransactionsByPositionId(p.getId()).orElse(0.0);
    }

    public double sumTransactionsByPositionAndDates(BudgetPosition position, int startMonth, int startYear, int endMonth, int endYear)
    {
        return this.repository.sumTransactionsByPositionIdAndDates(position.getId(), startMonth, startYear, endMonth, endYear).orElse(0.0);
    }

    public double sumTransactionsByPositionAndMonth(BudgetPosition position, int month, int year)
    {
        return this.repository.sumTransactionsByPositionIdAndMonth(position.getId(), month, year).orElse(0.0);
    }

    public double sumTransactionsByPositionAndQuarter(BudgetPosition position, int month, int year)
    {
//        TODO jak przekazać drugi warunek brzegowy aktualnego kwartału
//        TODO switch na wartość miesiąca? i zwraca pierwszy miesiąc kwartału?
//        jeśli kwartał jest istotny to można by dodać kolumne w bazie
//        return this.repository.sumTransactionsByPositionIdAndQuarter(position.getId(), month, year);
//        TODO tymczasowe żeby nie wywalało błędu
        return 0;
    }

    public double sumTransactionsByPositionAndYear(BudgetPosition position, int year) {
        return this.repository.sumTransactionsByPositionIdAndYear(position.getId(), year).orElse(0.0);
    }

    public List<TransactionDTO> readTransactionsDtoByBudget(Budget budget)
    {
        return this.repository.findTransactionsByBudgetId(budget.getId()).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public double sumTransactionsByPositionId(int id)
    {
        return this.repository.sumTransactionsByPositionId(id).orElse(0.0);
    }

    public double sumTransactionsByBudgetId(int id)
    {
        return this.repository.sumTransactionsByBudgetId(id).orElse(0.0);
    }

    public double sumTransactionsByBudgetIdAndType(int budgetId, Type type)
    {
        return this.repository.sumTransactionsByBudgetIdAndType(budgetId, type.name()).orElse(0.0);
    }

    public List<TransactionDTO> readTransactionsByBudgetPositionIdAsDto(int positionId)
    {
        return readTransactionsByPositionId(positionId).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public List<TransactionDTO> readTransactionsByAccountIdAsDto(int accountId)
    {
        return readTransactionsByAccountId(accountId).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public List<Transaction> readTransactionsByAccountId(int accountId)
    {
        return this.repository.findTransactionsByAccountId(accountId);
    }

    public double sumTransactionsByBudgetIdAndAccountIdAndType(int budgetId, int accountId, Type type)
    {
        return this.repository.sumTransactionsByBudgetIdAndAccountIdAndType(budgetId, accountId, type.name()).orElse(0.0);
    }

    public double sumTransactionsByDayAdnPositionId(String day, int positionId) 
    {
        return this.repository.sumTransactionsByDayAdnPositionId(Integer.valueOf(day), positionId).orElse(0.0);
    }

    public List<TransactionDTO> readTransactionsByMainCategoryIdAsDto(int mainCatId)
    {
        return this.repository.findTransactionsByMainCategoryId(mainCatId).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public List<Transaction> readTransactionsByMainCategoryId(int mainCatId)
    {
        return this.repository.findTransactionsByMainCategoryId(mainCatId);
    }

    public List<Transaction> readTransactionsBySubCategoryId(int subCatId)
    {
        return this.repository.findTransactionsBySubCategoryId(subCatId);
    }

    public List<TransactionDTO> readTransactionsBySubCategoryIdAsDto(int subCatId)
    {
        return this.repository.findTransactionsBySubCategoryId(subCatId).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public List<TransactionDTO> readTransactionsByTypeNameAsDto(String t)
    {
        return this.repository.findTransactionsByTypeName(t).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public List<Transaction> readTransactionsByTypeName(String t)
    {
        return this.repository.findTransactionsByTypeName(t);
    }

    public List<Integer> readCategoriesIdsByBillId(int billId)
    {
        return this.repository.findCategoriesIdByBillId(billId);
    }
}
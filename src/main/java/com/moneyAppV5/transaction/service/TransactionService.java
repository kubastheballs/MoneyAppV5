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

@Service
public class TransactionService
{
    private TransactionRepository repository;
    private GainerRepository gainerRepository;
    private PayeeRepository payeeRepository;
//    private BudgetService budgetService;

    TransactionService(TransactionRepository repository, GainerRepository gainerRepository, PayeeRepository payeeRepository)
//    TransactionService(TransactionRepository repository, GainerRepository gainerRepository, PayeeRepository payeeRepository, BudgetService budgetService)
    {
        this.repository = repository;
        this.gainerRepository = gainerRepository;
        this.payeeRepository = payeeRepository;
//        this.budgetService = budgetService;
    }

    public boolean existsById(int id)
    {
        return this.repository.existsById(id);
    }

    public Transaction createTransaction(final TransactionDTO toSave)
    {
//        TODO przy tworzeniu transakcji należy sprawdzić czy istnieje budgetPos dla daty i kategorii - jesli tak to dodać a jeśli nie to utworzyć nowy
//TODO ewentualnie zostawiamy to dla widoku budżetu

        return this.repository.save(toSave.toTransaction());
    }

    public Gainer createGainer(final GainerDTO toSave)
    {
        return this.gainerRepository.save(toSave.toGainer());
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

    public List<Gainer> readAllGainers()
    {
        return this.gainerRepository.findAll();
    }

    public List<Payee> readAllPayees()
    {
        return this.payeeRepository.findAll();
    }

    public Optional<Transaction> readTransactionById(int id)
    {
        return this.repository.findById(id);
    }

    public Optional<Gainer> readGainerById(int id)
    {
        return this.gainerRepository.findById(id);
    }

    public Optional<Payee> readPayeeById(int id)
    {
        return this.payeeRepository.findById(id);
    }

    public List<TransactionDTO> readAllTransactionsDTO()
    {
        List<TransactionDTO> dtos = new ArrayList<>();

        for(Transaction tran : readAllTransactions())
            dtos.add(new TransactionDTO(tran));

        return dtos;
    }

    public List<PayeeDTO> readAllPayeesDto()
    {
        List<PayeeDTO> dtos = new ArrayList<>();

        for(Payee pay : readAllPayees())
            dtos.add(new PayeeDTO(pay));

        return dtos;
    }

    public List<GainerDTO> readAllGainersDto()
    {
        List<GainerDTO> dtos = new ArrayList<>();

        for(Gainer gai : readAllGainers())
            dtos.add(new GainerDTO(gai));

        return dtos;
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
        List<TransactionDTO> dtos = new ArrayList<>();

        for (Transaction t : this.repository.findByPayeeId(id))
            dtos.add(new TransactionDTO(t));

        return dtos;
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
        List<TransactionDTO> dtos = new ArrayList<>();

        for (Transaction t : this.repository.findTransactionsByBudgetId(budget.getId()))
            dtos.add(new TransactionDTO(t));

        return dtos;
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
        var list = new ArrayList<TransactionDTO>();

        for (Transaction t : readTransactionsByPositionId(positionId))
            list.add(new TransactionDTO(t));

        return list;
    }

    public List<TransactionDTO> readTransactionsByAccountIdAsDto(int accountId)
    {
        var list = new ArrayList<TransactionDTO>();

        for (Transaction t : readTransactionsByAccountId(accountId))
            list.add(new TransactionDTO(t));

        return list;
    }

    public List<Transaction> readTransactionsByAccountId(int accountId)
    {
        return this.repository.findTransactionsByAccountId(accountId);
    }
}

package com.moneyAppV5.budget.service;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.budget.repository.BudgetPositionRepository;
import com.moneyAppV5.budget.repository.BudgetRepository;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public
class BudgetService
{
    private final BudgetRepository repository;
    private final BudgetPositionRepository positionsRepository;
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    BudgetService(BudgetRepository repository, BudgetPositionRepository positionsRepository, TransactionService transactionService, CategoryService categoryService)
    {
        this.repository = repository;
        this.positionsRepository = positionsRepository;
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    List<BudgetPosition> readBudgetPositionsByBudgetIdAndType(int id, Type type)
    {
        return this.positionsRepository.findPositionsByBudgetIdAndType(id, type.name());
    }

    BudgetPositionDTO readBudgetPositionAsDto(BudgetPosition bp)
    {
        var position = new BudgetPositionDTO(bp);
        var actual = sumTransactionsByPositionId(bp.getId());

        position.setActualAmount(actual);
        position.setBalance(position.getPlannedAmount() - actual);
        position.setTransactionsDto(this.transactionService.readTransactionsByBudgetPositionIdAsDto(bp.getId()));

        return position;
    }

    private List<BudgetPositionDTO> readBudgetPositionsByBudgetIdAndTypeAsDto(int budgetId, Type type)
    {
        var dtos = new ArrayList<BudgetPositionDTO>();

        for (BudgetPosition bp : readBudgetPositionsByBudgetIdAndType(budgetId, type))
            dtos.add(readBudgetPositionAsDto(bp));

        return dtos;
    }

    public BudgetDTO readBudgetAsDto(Budget b)
    {
        var budget = new BudgetDTO(b);

        budget.setIncomesDto(readBudgetPositionsByBudgetIdAndTypeAsDto(b.getId(), Type.INCOME));
        budget.setExpensesDto(readBudgetPositionsByBudgetIdAndTypeAsDto(b.getId(), Type.EXPENSE));
        budget.setTransactionsDto(readTransactionsByBudgetIdAsDto(b.getId()));

        budget.setPlannedIncomes(sumPlannedByList(budget.getIncomesDto()));
        budget.setActualIncomes(sumActualByList(budget.getIncomesDto()));

        budget.setPlannedExpenses(sumPlannedByList(budget.getExpensesDto()));
        budget.setActualExpenses(sumActualByList(budget.getExpensesDto()));

        budget.setBalancePlanned(budget.getPlannedIncomes() - budget.getPlannedExpenses());
        budget.setBalanceActual(budget.getActualIncomes() - budget.getActualExpenses());

        budget.setBalanceIncomes(budget.getPlannedIncomes() - budget.getActualIncomes());
        budget.setBalanceExpenses(budget.getPlannedExpenses() - budget.getActualExpenses());

        return budget;
    }

    private double sumPlannedByList(List<BudgetPositionDTO> list)
    {
        double sum = 0;

        for (BudgetPositionDTO bp : list)
            sum += bp.getPlannedAmount();

        return sum;
    }

    private double sumActualByList(List<BudgetPositionDTO> list)
    {
        double sum = 0;

        for (BudgetPositionDTO bp : list)
            sum += bp.getActualAmount();

        return sum;
    }

    private double sumPlannedByBudgetIdAndType(int budgetId, Type type)
    {
        return this.positionsRepository.sumPlannedByBudgetIdAndType(budgetId, type.name()).orElse(0.0);
    }

    public Budget readBudgetById(int id)
    {
        return this.repository.findById(id).orElseThrow();
    }

    public Budget createBudget(BudgetDTO current)
    {
        return this.repository.save(current.toBudget());
    }

    public boolean existsByMonthAndYear(int month, int year)
    {
        return this.repository.existsByMonthAndYear(month, year);
    }

    public List<BudgetPosition> createPositionsListByBudget(Budget budget)
    {
        List<BudgetPosition> positions = new ArrayList<>();

        for (Category cat : this.categoryService.readAllCategories())
        {
            var pos = this.positionsRepository.save(new BudgetPosition(cat, budget));
            positions.add(pos);
        }

        return positions;
    }

//    TODO analogiczna metoda dla minimalnego?
    public Budget readBudgetWithHighestTransactionsSumByCategory(Category category)
    {
        var values = new HashMap<Integer, Double>();

        for (BudgetPosition p : this.positionsRepository.findPositionsByCategoryId(category.getId()))
            values.put(p.getHash(), this.transactionService.sumTransactionsByBudgetPosition(p));

        Integer key = Collections.max(values.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();

        return readBudgetByHash(key);
    }

    public List<BudgetPosition> readPositionsByBudgetId(int id)
    {
        return this.positionsRepository.findPositionsByBudgetId(id);
    }

    double sumTransactionsByPositionId(int id)
    {
        return this.transactionService.sumTransactionsByPositionId(id);
    }

    public List<BudgetDTO> readAllBudgetsDto()
    {
        List<BudgetDTO> dtos = new ArrayList<>();

        for (Budget b : readAllBudgets())
            dtos.add(new BudgetDTO(b));

        return dtos;
    }

    private List<Budget> readAllBudgets()
    {
        return this.repository.findAll();
    }

    public Budget readBudgetByHash(Integer hash)
    {
        return this.repository.findByHash(hash);
    }

    public BudgetPosition readPositionByHash(Integer hash) {
        return this.positionsRepository.findByHash(hash);
    }

    public List<BudgetPositionDTO> readPositionsDtoByBudgetAndType(Budget budget, Type type)
    {
        List<BudgetPositionDTO> dtos = new ArrayList<>();

        for (BudgetPosition bp : this.positionsRepository.findPositionsByBudgetIdAndType(budget.getId(), type.name()))
        {
            var p = new BudgetPositionDTO(bp);
            p.setActualAmount(sumTransactionsByPositionId(bp.getId()));

            dtos.add(p);
        }

        return dtos;
    }

    public BudgetPosition readPositionByBudgetHashAndCategory(Integer hash, Category category)
    {
        return this.positionsRepository.findByHashAndCategoryId(hash, category.getId());
    }

    public Integer readNewestBudgetHash()
    {
        return this.repository.findNewestBudgetHash().orElse(null);
    }

    public double sumTransactionsByPositionAndMonth(BudgetPosition position, int month, int year) {
        return this.transactionService.sumTransactionsByPositionAndMonth(position, month, year);
    }

    public double sumTransactionsByPositionAndQuarter(BudgetPosition position, int month, int year) {
        return this.transactionService.sumTransactionsByPositionAndQuarter(position, month, year);
    }

    public double sumTransactionsByPositionAndYear(BudgetPosition position, int year) {
        return this.transactionService.sumTransactionsByPositionAndYear(position, year);
    }

    public List<TransactionDTO> readTransactionsDtoByBudget(Budget budget)
    {
        return this.transactionService.readTransactionsDtoByBudget(budget);
    }

    public void checkPositionsByBudget(Budget budget)
    {
        var positions = new HashMap<Category, BudgetPosition>();
//TODO obczaić wykoanie przez stream i Collector (todo-app?)
        for (BudgetPosition p : readPositionsByBudgetId(budget.getId()))
            positions.put(p.getCategory(), p);

        for (Category cat : this.categoryService.readAllCategories())
            if  (!positions.containsKey(cat))
            {
                var pos = this.positionsRepository.save(new BudgetPosition(cat, budget));
                positions.put(cat, pos);
            }
//TODO to raczej zadanie dla transactionService
        var transactions = this.transactionService.readTransactionsByBudgetId(budget.getId());

        for (Transaction t : transactions)
            if  (positions.containsKey(t.getCategory()))
                this.transactionService.updateBudgetDataInTransaction(t.getId(), positions.get(t.getCategory()));
    }

    public BudgetDTO readBudgetPlanAsDto(Budget b)
    {
        var budget = new BudgetDTO(b);

        budget.setIncomesDto(readBudgetPositionsByBudgetIdAndTypeAsDto(b.getId(), Type.INCOME));
        budget.setExpensesDto(readBudgetPositionsByBudgetIdAndTypeAsDto(b.getId(), Type.EXPENSE));

        return budget;
    }

    private List<TransactionDTO> readTransactionsByBudgetIdAsDto(int budgetId)
    {
        var list = new ArrayList<TransactionDTO>();

        for (Transaction t : this.transactionService.readTransactionsByBudgetId(budgetId))
            list.add(new TransactionDTO(t));

        return list;
    }
}

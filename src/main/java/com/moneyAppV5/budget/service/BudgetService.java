package com.moneyAppV5.budget.service;

import com.moneyAppV5.account.dto.AccountBudgetsCountsDTO;
import com.moneyAppV5.account.dto.AccountBudgetsSumsDTO;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.budget.dto.BudgetPositionsWrapperDTO;
import com.moneyAppV5.budget.repository.BudgetPositionRepository;
import com.moneyAppV5.budget.repository.BudgetRepository;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.category.dto.CategoryDTO;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
public class BudgetService
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

        position.setCategory(new CategoryDTO(bp.getCategory()));
        position.setActualAmount(actual);
        position.setBalance(position.getPlannedAmount() - actual);
        position.setTransactionsDto(this.transactionService.readTransactionsByBudgetPositionIdAsDto(bp.getId()));

        return position;
    }

    public BudgetPositionDTO readBudgetPositionViewAsDto(BudgetPosition bp)
    {
        var position = new BudgetPositionDTO();

        position.setHash(bp.getHash());
        position.setPlannedAmount(bp.getPlannedAmount());
        position.setActualAmount(this.transactionService.sumTransactionsByPositionId(bp.getId()));
        position.setUsage((position.getActualAmount() / position.getPlannedAmount()) * 100);
        position.setBudgetDto(new BudgetDTO(bp.getBudget()));
        position.setCategory(new CategoryDTO(bp.getCategory()));
        position.setDailyView(sumDailyTransactionsByPositionIdAndMonth(bp.getId(), Month.of(bp.getBudget().getMonth()).length(Year.isLeap(bp.getBudget().getMonth()))));
        position.setTransactionsDto(this.transactionService.readTransactionsByBudgetPositionIdAsDto(bp.getId()));

//        TODO
//        stats

        return position;
    }

    public LinkedHashMap<String, Double> sumDailyTransactionsByPositionIdAndMonth(int positionId, int monthLength)
    {
        var map = new LinkedHashMap<String, Double>();

        for (String day : monthDaysList(monthLength))
            map.put(day, this.transactionService.sumTransactionsByDayAdnPositionId(day, positionId));

        return map;
    }

    public List<String> monthDaysList(int monthLength)
    {
        var days = new ArrayList<String>();

        for (int i = 1; i <= monthLength; i++)
            days.add(String.valueOf(i));

        return days;

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

    Budget readBudgetByMonthAndYear(int month, int year)
    {
//        TODO jaki błąd?
        return this.repository.findByMonthAndYear(month, year).orElse(new Budget());
    }

//    public BudgetDTO readBudgetOnlyWithActualByAccountIdAndMonthAsDto(int month, int year, int accountId)
//    {
//        var b = readBudgetByMonthAndYear(month, year);
//        var budget = new BudgetDTO(b);
//
//        budget.setActualIncomes(this.transactionService.sumTransactionsByBudgetIdAndAccountIdAndType(b.getId(), accountId, Type.INCOME));
//
//        budget.setActualExpenses(this.transactionService.sumTransactionsByBudgetIdAndAccountIdAndType(b.getId(), accountId, Type.EXPENSE));
//
//        budget.setBalanceActual(budget.getActualIncomes() - budget.getActualExpenses());
//
//        return budget;
//    }

    public BudgetDTO readBudgetOnlyWithActualByAccountIdAndMonthAsDto(int[] date, int accountId)
    {
        var b = readBudgetByMonthAndYear(date[0], date[1]);
        var budget = new BudgetDTO(b);

        budget.setActualIncomes(this.transactionService.sumTransactionsByBudgetIdAndAccountIdAndType(b.getId(), accountId, Type.INCOME));

        budget.setActualExpenses(this.transactionService.sumTransactionsByBudgetIdAndAccountIdAndType(b.getId(), accountId, Type.EXPENSE));

        budget.setBalanceActual(budget.getActualIncomes() - budget.getActualExpenses());

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

    public void createPositionsListByBudget(Budget budget)
    {
        for (Category cat : this.categoryService.readAllCategories())
        {
            var pos = this.positionsRepository.save(new BudgetPosition(cat, budget));
        }
    }

    public AccountBudgetsCountsDTO readBudgetsWithMaxMinTransactionCountsByAccountIdAsDto(int accountId)
    {
        var map = new HashMap<Integer, Integer>();

        for (Transaction t : this.transactionService.readTransactionsByAccountId(accountId))
        {
            var k = t.getBudget().getHash();
            var v = map.get(k);

            if (!map.containsKey(k))
                map.put(k, 1);
            else
                map.replace(k, v + 1);
        }
//        TODO obsługa pustej mapy

        AccountBudgetsCountsDTO data;

        if  (map.size() > 0)
        {
            var maxKey = Collections.max(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
            var minKey = Collections.min(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();

            data = new AccountBudgetsCountsDTO(new BudgetDTO(readBudgetByHash(maxKey)), map.get(maxKey), new BudgetDTO(readBudgetByHash(minKey)), map.get(minKey));
        }
        else
            data = new AccountBudgetsCountsDTO();

        return data;
    }

    public AccountBudgetsSumsDTO readBudgetsWithMaxMinTransactionSumsByAccountIdAsDto(int accountId)
    {
        var map = new HashMap<Integer, Double>();

        for (Transaction t : this.transactionService.readTransactionsByAccountId(accountId))
        {
            var k = t.getBudget().getHash();
            var v = t.getAmount();

            if (!map.containsKey(k))
                map.put(k, v);
            else
                map.replace(k, v + map.get(k));
        }

        AccountBudgetsSumsDTO data;

//        TODO postawienie warunku? != null?
        if  (map.size() > 0)
        {
            var maxKey = Collections.max(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
            var minKey = Collections.min(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();

            data = new AccountBudgetsSumsDTO(new BudgetDTO(readBudgetByHash(maxKey)), map.get(maxKey), new BudgetDTO(readBudgetByHash(minKey)), map.get(minKey));
        }
        else
            data = new AccountBudgetsSumsDTO();

        return data;
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
        return this.positionsRepository.findByHash(hash).orElseThrow();
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
    public BudgetDTO readBudgetByMonthAndYearAsDto(int month, int year)
    {
        return new BudgetDTO(readBudgetByMonthAndYear(month, year));
    }

    public void updatePlannedAmountInPositions(BudgetPositionsWrapperDTO current)
    {
        for (BudgetPositionDTO bp : current.getList())
        {
            if (this.positionsRepository.existsByHash(bp.getHash()))
                this.positionsRepository.setPlannedAmountByPositionHash(bp.getPlannedAmount(), bp.getHash());
        }
    }

    public List<BudgetPositionDTO> readPositionsByBudgetIdAsDto(Integer budgetId)
    {
        var list = new ArrayList<BudgetPositionDTO>();

        for (BudgetPosition bp : readPositionsByBudgetId(budgetId))
            list.add(new BudgetPositionDTO(bp));

        return list;
    }

    public BudgetPositionsWrapperDTO readPositionsWrapperAsDto(int hash)
    {
        var positions = new BudgetPositionsWrapperDTO();
        positions.setList(readPositionsByBudgetHashAsDto(hash));

        return positions;
    }

    private List<BudgetPositionDTO> readPositionsByBudgetHashAsDto(Integer hash)
    {
        var budgetId = readBudgetIdByBudgetHash(hash);

        var list = new ArrayList<BudgetPositionDTO>();

        for (BudgetPosition bp : readPositionsByBudgetId(budgetId))
            list.add(new BudgetPositionDTO(bp));

        return list;
    }

    private int readBudgetIdByBudgetHash(Integer hash)
    {
        return this.repository.readBudgetIdByBudgetHash(hash).orElseThrow();
    }
}

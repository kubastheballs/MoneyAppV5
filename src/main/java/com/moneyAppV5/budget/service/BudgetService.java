package com.moneyAppV5.budget.service;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.repository.BudgetPositionRepository;
import com.moneyAppV5.budget.repository.BudgetRepository;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public
class BudgetService
{
    private final BudgetRepository repository;
    private final BudgetPositionRepository positionsRepository;
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    List<BudgetPosition> budgetPositions;
    List<Transaction> transactions;

    BudgetService(BudgetRepository repository, BudgetPositionRepository positionsRepository, TransactionService transactionService, CategoryService categoryService)
    {
        this.repository = repository;
        this.positionsRepository = positionsRepository;
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    BudgetPosition createPosition(com.moneyAppV5.budget.dto.BudgetPositionDTO toSave)
    {
        return this.positionsRepository.save(toSave.toPosition());
    }

    List<BudgetPosition> createExpensePositionsByMonthAndYear(int month, int year)
    {
        List<BudgetPosition> positions = new ArrayList<>();
        List<Transaction> transactions = readTransactionsByMonthAndYear(month, year);

//        TODO
//        for (Transaction t : transactions)
//        if (t.getCategory())


        return null;
    }

    List<BudgetPosition> readBudgetPositionsByBudgetId(Integer id)
    {
        return this.positionsRepository.findPositionsByBudgetId(id);
    }

    List<BudgetPosition> readIncomeBudgetPositionsByBudgetId(Integer id)
    {
        return this.positionsRepository.findIncomePositionsByBudgetId(id);
    }

    List<BudgetPosition> readExpenseBudgetPositionsByBudgetId(Integer id)
    {
        return this.positionsRepository.findExpensePositionsByBudgetId(id);
    }

    public List<com.moneyAppV5.budget.dto.BudgetPositionDTO> readIncomeBudgetPositionsDtoByBudgetId(Integer id)
    {
        List<com.moneyAppV5.budget.dto.BudgetPositionDTO> positions = new ArrayList<>();

        for (BudgetPosition b : readIncomeBudgetPositionsByBudgetId(id))
            positions.add(new com.moneyAppV5.budget.dto.BudgetPositionDTO(b));

        return positions;
    }

    public List<com.moneyAppV5.budget.dto.BudgetPositionDTO> readExpenseBudgetPositionsDtoByBudgetId(Integer id)
    {
        List<com.moneyAppV5.budget.dto.BudgetPositionDTO> positions = new ArrayList<>();

        for (BudgetPosition b : readExpenseBudgetPositionsByBudgetId(id))
            positions.add(new com.moneyAppV5.budget.dto.BudgetPositionDTO(b));

        return positions;
    }

//    TODO adekwatne metody dla actualIncomes i wydatków + metody na salda
    double readPlannedIncomesByBudgetId(Integer id)
    {
    double sum = 0;

    for (BudgetPosition b : readIncomeBudgetPositionsByBudgetId(id))
        sum += b.getPlannedAmount();

    return sum;
    }

    double sumActualExpensesByMonthAndYear(int month, int year)
    {
        double sum = 0;

        for (Transaction t : readExpenseTransactionsByMonthAndYear(month, year))
            sum += t.getAmount();

        return -sum;
    }

    private List<Transaction> readExpenseTransactionsByMonthAndYear(int month, int year)
    {
        List<Transaction> ex = new ArrayList<>();

        for (Transaction t : this.transactionService.readTransactionsByMonthAndYear(month, year))
            if ((t.getCategory().getType()).equals(Type.EXPENSE))
                ex.add(t);

        return ex;
    }


    public Budget readBudgetById(int id)
    {
        return this.repository.findById(id).orElseThrow();
    }

    public BudgetDTO readBudgetDtoById(int id)
    {
        var dto = new BudgetDTO(readBudgetById(id));

        readTransactionsByMonthAndYear(dto.getMonth(), dto.getYear());

        dto.setPlannedIncomes(readPlannedIncomesByBudgetId(id));
//        dto.setActualIncomes();
//        TODO w sumie to tutaj można zrolować to do metody readPositionsByBudgetIdAndType(id, Type.TYP)
//        TODO tylko że zdaje się że takie przekazanie do sql wywalało błąd hexadecimal coś tam
        dto.setActualExpenses(sumActualExpensesByBudgetId(id));

        dto.setBalanceIncomes(dto.getPlannedIncomes() - dto.getActualIncomes());
        dto.setBalanceExpenses(dto.getPlannedExpenses() - dto.getActualExpenses());

        dto.setBalancePlanned(dto.getPlannedIncomes() - dto.getPlannedExpenses());
        dto.setBalanceActual(dto.getActualIncomes() - dto.getActualExpenses());

        dto.setIncomes(readIncomeBudgetPositionsByBudgetId(id));
        dto.setExpenses(readExpensePositionsByBudgetId(id));

        return dto;
//        return new BudgetDTO(readBudgetById(id));
    }

    private double sumActualExpensesByPositionId(int id)
    {
        double sum = 0;

        for (Transaction t : readTransactionsByPositionId(id))
            sum += t.getAmount();

        return sum;
    }

    private double sumActualExpensesByBudgetId(int id)
    {
        double sum = 0;

        for (BudgetPosition bp : readExpensePositionsByBudgetId(id))
            for (Transaction t :bp.getTransactions())
                sum += t.getAmount();

        return -sum;
    }

    void assignTransactionsToBudget(Budget budget, List<Transaction> transactions)
    {
        for (Transaction t : transactions)
            if (t.getBudget() == null)
                t.setBudget(budget);
    }

    List<Transaction> readTransactionsByMonthAndYear(int month, int year)
    {
        var transactions = this.transactionService.readTransactionsByMonthAndYear(month, year);

        assignTransactionsToBudget(this.repository.findByMonthAndYear(month, year), transactions);

        return transactions;
    }

    List<Transaction> readTransactionsByBudgetId(int id)
    {
        return this.transactionService.readTransactionsByBudgetId(id);
    }

    List<Transaction> readTransactionsByPositionId(Integer id)
    {
        return this.transactionService.readTransactionsByPositionId(id);
    }

    public Budget createBudget(BudgetDTO current)
    {
        return this.repository.save(current.toBudget());
    }

    public boolean existsByMonthAndYear(int month, int year)
    {
        return this.repository.existsByMonthAndYear(month, year);
    }

    public Budget readBudgetByMonthAndYear(int month, int year)
    {
        return this.repository.findByMonthAndYear(month, year);
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

    public List<BudgetPosition> readPositionsByBudgetId(int id)
    {
        return this.positionsRepository.findPositionsByBudgetId(id);
    }

    void assignTransactionsToPosition(BudgetPosition position, List<Transaction> transactions)
    {
        for (Transaction t : transactions)
        {
            if (t.getBudgetPosition() == null)
            {
                t.setBudgetPosition(position);
                t.setBudget(position.getBudget());

                this.transactionService.updateBudgetDataInTransaction(t.getId(), position);
            }
        }
    }

    public List<BudgetPosition> readExpensePositionsByBudgetId(int id)
    {
        List<BudgetPosition> ex = new ArrayList<>();

        for (BudgetPosition bp : readPositionsByBudgetId(id))
            if ((bp.getCategory().getType()).equals(Type.EXPENSE))
                ex.add(bp);

            for (BudgetPosition b : ex)
                assignTransactionsToPosition(b, readTransactionsByMonthAndYear(b.getBudget().getMonth(), b.getBudget().getYear()));

        return ex;
    }

    public List<com.moneyAppV5.budget.dto.BudgetPositionDTO> readExpensePositionsByBudgetIdDto(int id)
    {
        List<com.moneyAppV5.budget.dto.BudgetPositionDTO> dtos = new ArrayList<>();

        for (BudgetPosition bp : readExpensePositionsByBudgetId(id))
        {
            com.moneyAppV5.budget.dto.BudgetPositionDTO dto = new com.moneyAppV5.budget.dto.BudgetPositionDTO(bp);
            dto.setActualAmount(sumActualExpensesByPositionId(bp.getId()));
            dtos.add(dto);
        }

        return dtos;
    }

    public BudgetPosition readPositionByBudgetIdAndCategory(Integer id, Category category)
    {
        return this.positionsRepository.findByBudgetIdAndCategoryId(id, category.getId());
    }

    public List<BudgetDTO> readAllBudgetsDto()
    {
        List<BudgetDTO> dtos = new ArrayList();

        for (Budget b : readAllBudgets())
            dtos.add(new BudgetDTO(b));

        return dtos;
    }

    private List<Budget> readAllBudgets()
    {
        return this.repository.findAll();
    }


    //    TODO czy w metodzie powinno być categoryId czy category?

//    List<BudgetPosition> getBudgetPositionsByBudget(Budget budget)
//    {
//        this.budgetPositions = this.repository.getBudgetPositionsByBudget(budget);
//
//        return this.budgetPositions;
//    }

//    List<BudgetPosition> getBudgetPositionsByTypeFromBudget(Type type)
//    {
//        List<BudgetPosition> positions = new ArrayList<>();
//
//        for (BudgetPosition p : this.budgetPositions)
//            if ((p.getCategory().getType()).equals(type))
//                positions.add(p);
//
//        return positions;
//    }

//    List<Transaction> getTransactionsByTypeFromBudget(Budget budget, Type type)
//    {
//        this.transactions = this.repository.getTransactionsByTypeAndBudget(budget, type);
//
//        return this.transactions;
//    }

//    double sumActualTransactionsByCategoryAndMonth(Category category)
//    {
//        double sum = 0;
//
//        for (Transaction t : getActualTransactionsByCategoryAndMonth(category))
//            sum+=t.getAmount();
//
//        return sum;
//    }
//
//    double getActualAmountByCategory(Category category)
//    {
//        BudgetPosition pos = new BudgetPosition();
//
//        for (BudgetPosition b : this.budgetPositions)
//            if ((b.getCategory()).equals(category))
//                pos = b;
//
//        return pos.getActualAmount();
//    }
//
//    double getPlannedAmountByCategory(Category category)
//    {
//        BudgetPosition pos = new BudgetPosition();
//
//        for (BudgetPosition b : this.budgetPositions)
//            if ((b.getCategory()).equals(category))
//                pos = b;
//
//        return pos.getPlannedAmount();
//    }
//
//    double balancePlannedAndActualAmountByCategoryByMonth(Category category)
//    {
//        return getPlannedAmountByCategory(category) - getActualAmountByCategory(category);
//    }
//
//    double sumActualAmountsByMonth()
//    {
//        double sum = 0;
//
//        for (BudgetPosition b : this.budgetPositions)
//            sum += b.getActualAmount();
//
//        return sum;
//    }
//
//    double sumPlannedAmountsByMonth()
//    {
//        double sum = 0;
//
//        for (BudgetPosition b : this.budgetPositions)
//            sum += b.getPlannedAmount();
//
//        return sum;
//    }
//
//    double balanceActualAndPlannedAmountsByMonth()
//    {
//        return sumPlannedAmountsByMonth() - sumActualAmountsByMonth();
//    }
//
//    List<Transaction> getActualTransactionsByCategoryAndMonth(Category category)
//    {
//        List<Transaction> actualTransactions = new ArrayList<>();
//
//        for (Transaction t : this.transactions)
//            if ((t.getCategory()).equals(category))
//                actualTransactions.add(t);
//
//        return actualTransactions;
//    }

}

package com.moneyAppV5.budget.service;

import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.budget.repository.BudgetPositionRepository;
import com.moneyAppV5.budget.repository.BudgetRepository;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public
class BudgetService
{
    private final BudgetRepository repository;
    private final BudgetPositionRepository positionsRepository;
    private final TransactionService transactionService;

    List<BudgetPosition> budgetPositions;
    List<Transaction> transactions;

    BudgetService(BudgetRepository repository, BudgetPositionRepository positionsRepository, TransactionService transactionService)
    {
        this.repository = repository;
        this.positionsRepository = positionsRepository;
        this.transactionService = transactionService;
    }

    List<BudgetPosition> getBudgetPositionsByBudgetId(Integer id)
    {
        return this.positionsRepository.findPositionsByBudgetId(id);
    }

    List<BudgetPosition> getIncomeBudgetPositionsByBudgetId(Integer id)
    {
        return this.positionsRepository.findIncomePositionsByBudgetId(id);
    }

    List<BudgetPosition> getExpenseBudgetPositionsByBudgetId(Integer id)
    {
        return this.positionsRepository.findExpensePositionsByBudgetId(id);
    }

    public List<BudgetPositionDTO> getIncomeBudgetPositionsDtoByBudgetId(Integer id)
    {
        List<BudgetPositionDTO> positions = new ArrayList<>();

        for (BudgetPosition b : getIncomeBudgetPositionsByBudgetId(id))
            positions.add(new BudgetPositionDTO(b));

        return positions;
    }

//    TODO adekwatne metody dla actualIncomes i wydatków + metody na salda
    double getPlannedIncomesByBudgetId(Integer id)
    {
        double sum = 0;

        for (BudgetPosition b : getIncomeBudgetPositionsByBudgetId(id))
            sum += b.getPlannedAmount();

        return sum;
    }

    public BudgetDTO getBudgetDtoById(int id)
    {
//        TODO tu pewnie należy dać jakiś check czy budżet o danym id istnieje w bazie

        return new BudgetDTO(this.repository.findById(id).get());
    }

    List<Transaction> getTransactionsByMonthAndYear(Month month, Year year)
    {
        return this.transactionService.getTransactionsByMonthAndYear(month, year);
    }















    //    TODO czy w medotdzie powinno być categoryId czy category?

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

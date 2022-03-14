package com.moneyAppV5.budget.repository;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.Transaction;

import java.util.List;
import java.util.Optional;

public interface BudgetPositionRepository
{
    List<BudgetPosition> findAll();
    List<BudgetPosition> findPositionsByBudgetId(Integer id);
    List<BudgetPosition> findIncomePositionsByBudgetId(Integer id);
    List<BudgetPosition> findExpensePositionsByBudgetId(Integer id);

    Optional<BudgetPosition> findById(Integer id);

    BudgetPosition save(BudgetPosition entity);



//    List<BudgetPosition> getBudgetPositionsByTypeAndBudget(Budget budget, Type type);
//    List<BudgetPosition> getBudgetPositionsByBudget(Budget budget);

}

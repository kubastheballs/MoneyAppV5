package com.moneyAppV5.budget.repository;

import com.moneyAppV5.budget.BudgetPosition;

import java.util.List;
import java.util.Optional;

public interface BudgetPositionRepository
{
    List<BudgetPosition> findAll();
    List<BudgetPosition> findPositionsByBudgetId(Integer budgetId);
    List<BudgetPosition> findPositionsByCategoryId(Integer categoryId);
    List<BudgetPosition> findIncomePositionsByBudgetId(Integer id);
    List<BudgetPosition> findExpensePositionsByBudgetId(Integer id);

    Optional<BudgetPosition> findById(Integer id);
    //    TODO to prawdopodobnie też powinien być optional
    BudgetPosition findByBudgetIdAndCategoryId(Integer budgetId, Integer categoryId);

    BudgetPosition save(BudgetPosition entity);

    Optional<BudgetPosition> findByHash(Integer hash);

    List<BudgetPosition> findPositionsByBudgetIdAndType(Integer budgetId, String type);

    BudgetPosition findByHashAndCategoryId(Integer hash, int id);

    Optional<Double> sumPlannedByBudgetIdAndType(Integer budgetId, String type);

    void setPlannedAmountByPositionHash(Double planned, Integer hash);

//    List<BudgetPosition> getBudgetPositionsByTypeAndBudget(Budget budget, Type type);
//    List<BudgetPosition> getBudgetPositionsByBudget(Budget budget);

}

package com.moneyAppV5.budget.repository;

import com.moneyAppV5.budget.BudgetPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlBudgetPositionRepository extends BudgetPositionRepository, JpaRepository<BudgetPosition, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select * from BUDGET_POSITIONS where BUDGET_ID = :id")
    List<BudgetPosition> findPositionsByBudgetId(Integer id);

    @Override
    @Query(nativeQuery = true, value = "select distinct * from BUDGET_POSITIONS inner join categories on BUDGET_POSITIONS.CATEGORY_ID = CATEGORIES.ID " +
            "where BUDGET_ID = :id and CATEGORIES.TYPE = 'INCOME'")
    List<BudgetPosition> findIncomePositionsByBudgetId(Integer id);

    @Override
    @Query(nativeQuery = true, value = "select distinct * from BUDGET_POSITIONS inner join categories on BUDGET_POSITIONS.CATEGORY_ID = CATEGORIES.ID " +
                       "where BUDGET_ID = :id and CATEGORIES.TYPE = 'EXPENSE'")
    List<BudgetPosition> findExpensePositionsByBudgetId(Integer id);

    @Override
    @Query(nativeQuery = true, value = "select distinct * from BUDGET_POSITIONS inner join categories on BUDGET_POSITIONS.CATEGORY_ID = CATEGORIES.ID " +
            "where BUDGET_ID = :budgetId and CATEGORIES.TYPE = :type")
    List<BudgetPosition> findPositionsByBudgetIdAndType(Integer budgetId, String type);

//    @Override
//    @Query(nativeQuery = true, value = "select * from BUDGET_POSITIONS where BUDGET_ID = :budget.id")
//    List<BudgetPosition> getBudgetPositionsByBudget(Budget budget);
}

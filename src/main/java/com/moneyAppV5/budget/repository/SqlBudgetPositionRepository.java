package com.moneyAppV5.budget.repository;

import com.moneyAppV5.budget.BudgetPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Query(value = "select sum (PLANNED_AMOUNT) from BUDGET_POSITIONS inner join CATEGORIES" +
            " on BUDGET_POSITIONS.CATEGORY_ID = CATEGORIES.ID where (BUDGET_ID = :budgetId and " +
            "TYPE = :type)", nativeQuery = true)
    Optional<Double> sumPlannedByBudgetIdAndType(Integer budgetId, String type);

    @Override
    @Modifying
    @Transactional
    @Query(value = "update BUDGET_POSITIONS set PLANNED_AMOUNT = :planned where HASH = :hash", nativeQuery = true)
    void setPlannedAmountByPositionHash(Double planned, Integer hash);

//    @Override
//    @Query(nativeQuery = true, value = "select * from BUDGET_POSITIONS where BUDGET_ID = :budget.id")
//    List<BudgetPosition> getBudgetPositionsByBudget(Budget budget);
}

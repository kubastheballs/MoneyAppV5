package com.moneyAppV5.budget.repository;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlBudgetPositionRepository extends BudgetPositionRepository, JpaRepository<BudgetPosition, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select * from BUDGET_POSITIONS where BUDGET_ID = :budget.id")
    List<BudgetPosition> getBudgetPositionsByBudget(Budget budget);
}

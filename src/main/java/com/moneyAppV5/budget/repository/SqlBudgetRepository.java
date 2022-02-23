package com.moneyAppV5.budget.repository;

import com.moneyAppV5.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlBudgetRepository extends BudgetRepository, JpaRepository<Budget, Integer>
{

}

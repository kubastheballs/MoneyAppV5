package com.moneyAppV5.budget.repository;

import com.moneyAppV5.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlBudgetRepository extends BudgetRepository, JpaRepository<Budget, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select * from BUDGETS where month = :month and year = :year")
    Optional<Budget> findByMonthAndYear(Integer month, Integer year);

    @Override
    @Query(value = "select hash from budgets order by year, MONTH DESC limit 1", nativeQuery = true)
    Optional<Integer> findNewestBudgetHash();
}

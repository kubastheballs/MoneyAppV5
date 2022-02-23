package com.moneyAppV5.budget.repository;

import com.moneyAppV5.budget.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository
{
    List<Budget> findAll();

    Optional<Budget> findById(Integer id);
    boolean existsById(int id);

    Budget save(Budget entity);
}

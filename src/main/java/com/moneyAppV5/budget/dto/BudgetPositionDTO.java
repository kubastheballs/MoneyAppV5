package com.moneyAppV5.budget.dto;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class BudgetPositionDTO
{
    private Category category;
    private Budget budget;
    private List<Transaction> transactions;
    private double plannedAmount;
    private double actualAmount;
    private double balance;
    private String description;

     public BudgetPositionDTO()
    {
    }

//    TODO czy transakcje są potrzebne?

    BudgetPositionDTO(Category category, Budget budget, List<Transaction> transactions, double plannedAmount, double actualAmount, double balance, String description)
    {
        this.category = category;
        this.budget = budget;
        this.transactions = transactions;
        this.plannedAmount = plannedAmount;
        this.actualAmount = actualAmount;
        this.balance = balance;
        this.description = description;
    }

    BudgetPosition toBudgetPosition()
    {
        var result = new BudgetPosition();
        result.setCategory(this.category);
        result.setBudget(this.budget);
        result.setTransactions(new HashSet<>(this.transactions));
        result.setPlannedAmount(this.plannedAmount);
        result.setActualAmount(this.actualAmount);
        result.setBalance(this.balance);
        result.setDescription(this.description);

        return result;
    }
}

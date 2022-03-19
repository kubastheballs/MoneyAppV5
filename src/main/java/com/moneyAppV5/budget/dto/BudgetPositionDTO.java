package com.moneyAppV5.budget.dto;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.Transaction;

import java.util.HashSet;
import java.util.Set;

public class BudgetPositionDTO
{
    private Category category;
    private Budget budget;
    private Set<Transaction> transactions;
    private double plannedAmount;
    private double actualAmount;
    private double balance;
    private String description;

     public BudgetPositionDTO()
    {
    }

//    TODO czy transakcje są potrzebne?

    BudgetPositionDTO(Category category, Budget budget, Set<Transaction> transactions, double plannedAmount, double actualAmount, double balance, String description)
    {
        this.category = category;
        this.budget = budget;
        this.transactions = transactions;
        this.plannedAmount = plannedAmount;
        this.actualAmount = actualAmount;
        this.balance = balance;
        this.description = description;
    }

    public BudgetPositionDTO(BudgetPosition position)
    {
        this.category = position.getCategory();
//        this.budget = position.getBudget();
        this.transactions = position.getTransactions();
        this.plannedAmount = position.getPlannedAmount();
        this.actualAmount = position.getActualAmount();
        this.balance = position.getBalance();
        this.description = position.getDescription();
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getPlannedAmount() {
        return plannedAmount;
    }

    public void setPlannedAmount(double plannedAmount) {
        this.plannedAmount = plannedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BudgetPosition toPosition()
    {
        var result = new BudgetPosition();

        result.setCategory(this.category);
        result.setBudget(this.budget);
        result.setPlannedAmount(this.plannedAmount);
        result.setActualAmount(this.actualAmount);
        result.setBalance(this.balance);
        result.setDescription(this.description);

        return result;
    }
}

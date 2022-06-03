package com.moneyAppV5.account.dto;

import com.moneyAppV5.budget.dto.BudgetDTO;

public class AccountBudgetsSumsDTO
{
    private BudgetDTO highestSumBudget;
    private double highestSum;
    private BudgetDTO lowestSumBudget;
    private double lowestSum;

    public AccountBudgetsSumsDTO() {
    }

    public AccountBudgetsSumsDTO(BudgetDTO highestSumBudget, double highestSum, BudgetDTO lowestSumBudget, double lowestSum) {
        this.highestSumBudget = highestSumBudget;
        this.highestSum = highestSum;
        this.lowestSumBudget = lowestSumBudget;
        this.lowestSum = lowestSum;
    }

    public BudgetDTO getHighestSumBudget() {
        return highestSumBudget;
    }

    public void setHighestSumBudget(BudgetDTO highestSumBudget) {
        this.highestSumBudget = highestSumBudget;
    }

    public double getHighestSum() {
        return highestSum;
    }

    public void setHighestSum(double highestSum) {
        this.highestSum = highestSum;
    }

    public BudgetDTO getLowestSumBudget() {
        return lowestSumBudget;
    }

    public void setLowestSumBudget(BudgetDTO lowestSumBudget) {
        this.lowestSumBudget = lowestSumBudget;
    }

    public double getLowestSum() {
        return lowestSum;
    }

    public void setLowestSum(double lowestSum) {
        this.lowestSum = lowestSum;
    }
}

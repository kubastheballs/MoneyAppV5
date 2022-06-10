package com.moneyAppV5.budget.dto;

import com.moneyAppV5.budget.dto.BudgetDTO;

public class BudgetsCountsDTO
{
    private BudgetDTO highestCountBudget;
    private int highestCount;
    private BudgetDTO lowestCountBudget;
    private int lowestCount;

    public BudgetsCountsDTO() {
    }

    public BudgetsCountsDTO(BudgetDTO highestCountBudget, int highestCount, BudgetDTO lowestCountBudget, int lowestCount) {
        this.highestCountBudget = highestCountBudget;
        this.highestCount = highestCount;
        this.lowestCountBudget = lowestCountBudget;
        this.lowestCount = lowestCount;
    }

    public BudgetDTO getHighestCountBudget() {
        return highestCountBudget;
    }

    public void setHighestCountBudget(BudgetDTO highestCountBudget) {
        this.highestCountBudget = highestCountBudget;
    }

    public int getHighestCount() {
        return highestCount;
    }

    public void setHighestCount(int highestCount) {
        this.highestCount = highestCount;
    }

    public BudgetDTO getLowestCountBudget() {
        return lowestCountBudget;
    }

    public void setLowestCountBudget(BudgetDTO lowestCountBudget) {
        this.lowestCountBudget = lowestCountBudget;
    }

    public int getLowestCount() {
        return lowestCount;
    }

    public void setLowestCount(int lowestCount) {
        this.lowestCount = lowestCount;
    }
}

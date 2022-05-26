package com.moneyAppV5.budget.dto;

public class BudgetDtoWithSumDTO
{
    private BudgetDTO budget;
    private double sum;

    public BudgetDtoWithSumDTO()
    {
    }

    public BudgetDtoWithSumDTO(BudgetDTO budget, double sum) {
        this.budget = budget;
        this.sum = sum;
    }

    public BudgetDTO getBudget() {
        return budget;
    }

    public void setBudget(BudgetDTO budget) {
        this.budget = budget;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}

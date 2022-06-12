package com.moneyAppV5.budget.dto;

public class ActualBudgetsWrapperDTO
{
    private BudgetDTO actual;
    private BudgetDTO actualMinusOne;
    private BudgetDTO actualMinusTwo;


    public BudgetDTO getActual() {
        return actual;
    }

    public void setActual(BudgetDTO actual) {
        this.actual = actual;
    }

    public BudgetDTO getActualMinusOne() {
        return actualMinusOne;
    }

    public void setActualMinusOne(BudgetDTO actualMinusOne) {
        this.actualMinusOne = actualMinusOne;
    }

    public BudgetDTO getActualMinusTwo() {
        return actualMinusTwo;
    }

    public void setActualMinusTwo(BudgetDTO actualMinusTwo) {
        this.actualMinusTwo = actualMinusTwo;
    }
}

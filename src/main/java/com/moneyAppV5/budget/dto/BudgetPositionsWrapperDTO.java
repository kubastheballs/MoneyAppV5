package com.moneyAppV5.budget.dto;

import java.util.List;

public class BudgetPositionsWrapperDTO
{
    private List<BudgetPositionDTO> incomesList;
    private List<BudgetPositionDTO> expensesList;

    public List<BudgetPositionDTO> getIncomesList() {
        return incomesList;
    }

    public void setIncomesList(List<BudgetPositionDTO> incomesList) {
        this.incomesList = incomesList;
    }

    public List<BudgetPositionDTO> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<BudgetPositionDTO> expensesList) {
        this.expensesList = expensesList;
    }
}

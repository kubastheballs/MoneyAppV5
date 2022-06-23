package com.moneyAppV5.budget.dto;

import java.util.List;

public class YearBudgetDTO
{
    private int year;
    private List<BudgetDTO> months;
//    TODO


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<BudgetDTO> getMonths() {
        return months;
    }

    public void setMonths(List<BudgetDTO> months) {
        this.months = months;
    }
}

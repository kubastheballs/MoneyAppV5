package com.moneyAppV5.budget.dto;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;

import javax.persistence.OneToMany;
import java.time.Month;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class BudgetDTO
{
    private Month month;
    private Year year;
    private List<BudgetPosition> incomes;
    private List<BudgetPosition> expenses;
    private String description;

     public BudgetDTO()
    {
    }

    BudgetDTO(Month month, Year year, List<BudgetPosition> incomes, List<BudgetPosition> expenses, String description)
    {
        this.month = month;
        this.year = year;
        this.incomes = incomes;
        this.expenses = expenses;
        this.description = description;
    }

    Budget toBudget()
    {
        var result = new Budget();
        result.setMonth(this.month);
        result.setYear(this.year);
        result.setIncomes(new HashSet<>(this.incomes));
        result.setExpenses(new HashSet<>(this.expenses));
        result.setDescription(this.description);

        return result;
    }
}

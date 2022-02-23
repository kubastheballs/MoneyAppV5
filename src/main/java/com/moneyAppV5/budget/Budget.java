package com.moneyAppV5.budget;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="budgets")
public
class Budget
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Month month;
    private Year year;
    @OneToMany(mappedBy = "budget")
    private Set<BudgetPosition> incomes;
    @OneToMany(mappedBy = "budget")
    private Set<BudgetPosition> expenses;
    private String description;

     public Budget()
    {
    }

     public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Month getMonth()
    {
        return month;
    }

    public void setMonth(Month month)
    {
        this.month = month;
    }

    public Year getYear()
    {
        return year;
    }

    public void setYear(Year year)
    {
        this.year = year;
    }

    public Set<BudgetPosition> getIncomes()
    {
        return incomes;
    }

    public void setIncomes(Set<BudgetPosition> incomes)
    {
        this.incomes = incomes;
    }

    public Set<BudgetPosition> getExpenses()
    {
        return expenses;
    }

    public void setExpenses(Set<BudgetPosition> expenses)
    {
        this.expenses = expenses;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

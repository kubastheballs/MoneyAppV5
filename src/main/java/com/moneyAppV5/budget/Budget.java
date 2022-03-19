package com.moneyAppV5.budget;

import com.moneyAppV5.transaction.Transaction;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="budgets")
public
class Budget
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int month;
    private int year;
    @OneToMany(mappedBy = "budget")
    private Set<Transaction> transactions;
    @OneToMany(mappedBy = "budget")
    private Set<BudgetPosition> incomes;
    @OneToMany(mappedBy = "budget")
    private Set<BudgetPosition> expenses;
    private String description;

     public Budget()
    {
    }

     public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

//    public Month getMonth()
//    {
//        return month;
//    }
//
//    public void setMonth(Month month)
//    {
//        this.month = month;
//    }
//
//    public Year getYear()
//    {
//        return year;
//    }
//
//    public void setYear(Year year)
//    {
//        this.year = year;
//    }


    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}

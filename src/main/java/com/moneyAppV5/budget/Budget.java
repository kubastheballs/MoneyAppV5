package com.moneyAppV5.budget;

import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.transaction.Transaction;

import javax.persistence.*;
import java.util.Objects;
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
    private Integer hash;

     public Budget()
    {
    }

    public Budget( Integer month ,Integer year)
    {
        this.month = month;
        this.year = year;
    }

    private Budget(BudgetBuilder builder)
    {
        this.id = builder.id;
        this.month = builder.month;
        this.year = builder.year;
        this.transactions = builder.transactions;
        this.incomes = builder.incomes;
        this.expenses = builder.expenses;
        this.description = builder.description;
        this.hash = builder.hash;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.month, this.year);
    }

    public int getId() {
        return id;
    }

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

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public static class BudgetBuilder
    {
        private int id;
        private int month;
        private int year;
        private Set<Transaction> transactions;
        private Set<BudgetPosition> incomes;
        private Set<BudgetPosition> expenses;
        private String description;
        private Integer hash;

        public BudgetBuilder buildId(int id)
        {
            this.id = id;

            return this;
        }

        public BudgetBuilder buildMonth(int month)
        {
            this.month = month;

            return this;
        }

        public BudgetBuilder buildYear(int year)
        {
            this.year = year;

            return this;
        }

        public BudgetBuilder buildTransactions(Set<Transaction> transactions)
        {
            this.transactions = transactions;

            return this;
        }

        public BudgetBuilder buildIncomes(Set<BudgetPosition> incomes)
        {
            this.incomes = incomes;

            return this;
        }

        public BudgetBuilder buildExpenses(Set<BudgetPosition> expenses)
        {
            this.expenses = expenses;

            return this;
        }

        public BudgetBuilder buildDescription(String description)
        {
            this.description = description;

            return this;
        }
        public BudgetBuilder buildHash(Integer hash)
        {
            this.hash = hash;

            return this;
        }

        public Budget build()
        {
            return new Budget(this);
        }


    }
}

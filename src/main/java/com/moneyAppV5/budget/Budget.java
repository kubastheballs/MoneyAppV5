package com.moneyAppV5.budget;

import com.moneyAppV5.bill.Bill;
import com.moneyAppV5.budget.dto.BudgetDTO;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


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
    private Set<Bill> bills;
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
        this.bills = builder.bills;
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

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public BudgetDTO toDto()
    {
        return new BudgetDTO.BudgetDtoBuilder()
                .buildMonth(this.month)
                .buildYear(this.year)
                .buildName()
                .buildIncomesDto(this.incomes.stream().map(BudgetPosition::toDto).collect(Collectors.toList()))
                .buildExpensesDto(this.expenses.stream().map(BudgetPosition::toDto).collect(Collectors.toList()))
                .buildTransactionsDtoFromBillsDto(this.bills.stream().map(Bill::toDto).collect(Collectors.toList()))
                .buildDescription(this.description)
                .buildHash(this.hash)
                .build();
    }

    public static class BudgetBuilder
    {
        private int id;
        private int month;
        private int year;
        private Set<Bill> bills;
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

        public BudgetBuilder buildBills(Set<Bill> bills)
        {
            this.bills = bills;

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

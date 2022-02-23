package com.moneyAppV5.account;

import com.moneyAppV5.transaction.Transaction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private double target;
    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions;
    private double initBalance;
    @OneToMany(mappedBy = "account")
    private Set<MonthBalance> monthBalances;
    private double actualBalance;

//    TODO historia konta wprowadzana z ręki? tj stany kont z każdego pierwszego miesiąca wylcizane na podstawie transakcji oraz z ręki?

    public Account()
    {
    }

    public Account(String name, double actualBalance)
    {
        this.name = name;
        this.actualBalance = actualBalance;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getDeadline()
    {
        return deadline;
    }

    public void setDeadline(LocalDate deadline)
    {
        this.deadline = deadline;
    }

    public double getTarget()
    {
        return target;
    }

    public void setTarget(double target)
    {
        this.target = target;
    }

    public Set<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    public double getInitBalance()
    {
        return initBalance;
    }

    public void setInitBalance(double initBalance)
    {
        this.initBalance = initBalance;
    }

    public Set<MonthBalance> getMonthBalances()
    {
        return monthBalances;
    }

    public void setMonthBalances(Set<MonthBalance> monthBalances)
    {
        this.monthBalances = monthBalances;
    }

    public double getActualBalance()
    {
        return actualBalance;
    }

    public void setActualBalance(double actualBalance)
    {
        this.actualBalance = actualBalance;
    }

    public void updateFrom(final Account toUpdate)
    {
        this.name = toUpdate.getName();
        this.description = toUpdate.getDescription();
        this.deadline = toUpdate.getDeadline();
        this.target = toUpdate.getTarget();
        this.transactions = toUpdate.getTransactions();
        this.initBalance = toUpdate.getInitBalance();
        this.monthBalances = toUpdate.getMonthBalances();
        this.actualBalance = toUpdate.getActualBalance();
    }
}

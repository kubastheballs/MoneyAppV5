package com.moneyAppV5.bill.dto;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BillWriteModel
{
    private int day;
    private Payee payee;
    private Account account;
    private Budget budget;
    private List<Transaction> transactions = new ArrayList<>();
    private int hash;

    public BillWriteModel()
    {
        this.transactions.add(new Transaction());
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public double sumTransactions()
    {
        double sum = 0;

        for (Transaction t : this.transactions)
        {
            if ((t.getCategory().getType()).equals(Type.EXPENSE))
                sum -= t.getAmount();
            else
                sum += t.getAmount();
        }

        return sum;
    }

    public BillDTO toDto()
    {
        return new BillDTO.BillDtoBuilder()
                .buildDay(this.day)
                .buildPayee(this.payee.toDto())
                .buildAccount(this.account.toDto())
                .buildBudget(this.budget.toDto())
                .buildTransactions(this.transactions.stream().map(Transaction::toDto).collect(Collectors.toList()))
                .buildHash(this.hash)
                .buildSum()
                .build();
    }
}

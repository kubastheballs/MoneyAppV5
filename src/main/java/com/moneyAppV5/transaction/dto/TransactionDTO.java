package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;

import java.time.LocalDate;

public class TransactionDTO
{
    private LocalDate date;
    private Account account;
    private double amount;
    private Category category;
    private Payee payee;
    private Gainer gainer;
    private String description;
//    private BudgetPosition budgetPosition;


    private int accountId;
    private int categoryId;
    private int payeeId;
    private int gainerId;


     public TransactionDTO()
    {
    }

     public TransactionDTO(LocalDate date, Account account, double amount, Category category, Payee payee, Gainer gainer, String description)
    {
        this.date = date;
        this.account = account;
        this.amount = amount;
        this.category = category;
        this.payee = payee;
        this.gainer = gainer;
        this.description = description;
    }

     public TransactionDTO(LocalDate date, double amount, String description, int accountId, int categoryId, int payeeId, int gainerId)
    {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.payeeId = payeeId;
        this.gainerId = gainerId;
    }

    public Transaction toTransaction()
    {
        var result = new Transaction();
        result.setDate(this.date);
        result.setAccount(this.account);
        result.setAmount(this.amount);
        result.setCategory(this.category);
        result.setPayee(this.payee);
        result.setGainer(this.gainer);
        result.setDescription(this.description);

        return result;
    }

    public LocalDate getDate()
    {
        return date;
    }

    void setDate(LocalDate date)
    {
        this.date = date;
    }

    public double getAmount()
    {
        return amount;
    }

    void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }

    void setDescription(String description)
    {
        this.description = description;
    }

    public int getAccountId()
    {
        return accountId;
    }

    void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public int getPayeeId()
    {
        return payeeId;
    }

    void setPayeeId(int payeeId)
    {
        this.payeeId = payeeId;
    }

    public int getGainerId()
    {
        return gainerId;
    }

    void setGainerId(int gainerId)
    {
        this.gainerId = gainerId;
    }
}

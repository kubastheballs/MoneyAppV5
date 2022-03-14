package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransactionDTO
{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;
//    private LocalDate date;
    private Account account;
    private String amount;
//    private double amount;
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

    public TransactionDTO(Transaction transaction)
    {
        this.date = String.valueOf(transaction.getDate());
        this.account = transaction.getAccount();
        this.amount = String.valueOf(transaction.getAmount());
        this.category = transaction.getCategory();
        this.payee = transaction.getPayee();
        this.gainer = transaction.getGainer();
        this.description = transaction.getDescription();
    }


     public TransactionDTO(String date, Account account, String amount, Category category, Payee payee, Gainer gainer, String description)
    {
        this.date = date;
        this.account = account;
        this.amount = amount;
        this.category = category;
        this.payee = payee;
        this.gainer = gainer;
        this.description = description;
    }

    public TransactionDTO(String date, Account account, double amount, Category category, Payee payee, Gainer gainer, String description)
    {
        this.date = date;
        this.account = account;
        this.amount = String.valueOf(amount);
        this.category = category;
        this.payee = payee;
        this.gainer = gainer;
        this.description = description;
    }

    public TransactionDTO(LocalDate date, Account account, double amount, Category category, Payee payee, Gainer gainer, String description)
    {
        this.date = String.valueOf(date);
//        this.date = date;
        this.account = account;
        this.amount = String.valueOf(amount);
        this.category = category;
        this.payee = payee;
        this.gainer = gainer;
        this.description = description;
    }

     public TransactionDTO(LocalDate date, double amount, String description, int accountId, int categoryId, int payeeId, int gainerId)
    {
        this.date = String.valueOf(date);
        this.amount = String.valueOf(amount);
        this.description = description;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.payeeId = payeeId;
        this.gainerId = gainerId;
    }

    public Transaction toTransaction()
    {
        var result = new Transaction();
        result.setDate(LocalDate.parse(this.date, DateTimeFormatter.ISO_DATE));
        result.setAccount(this.account);
        result.setAmount(Double.parseDouble(this.amount));
        result.setCategory(this.category);
        result.setPayee(this.payee);
        result.setGainer(this.gainer);
        result.setDescription(this.description);

        return result;
    }

//    public LocalDate getDate()
//    {
//        return date;
//    }
//
//    void setDate(LocalDate date)
//    {
//        this.date = date;
//    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public double getAmount()
//    {
//        return amount;
//    }
//
//    void setAmount(double amount)
//    {
//        this.amount = amount;
//    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public Gainer getGainer() {
        return gainer;
    }

    public void setGainer(Gainer gainer) {
        this.gainer = gainer;
    }
}

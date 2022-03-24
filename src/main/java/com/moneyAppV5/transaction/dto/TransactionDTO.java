package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionDTO
{
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;
//    private LocalDate date;
    private Account account;
//    private String amount;
    private double amount;
    private Category category;
    private Payee isPaid;
    private Payee forWhom;
    private String description;
//    private BudgetPosition budgetPosition;

    private Budget budget;
    private BudgetPosition budgetPosition;

    private int accountId;
    private int categoryId;
    private int payeeId;
    private int gainerId;

    private int day;
    private int month;
    private int year;


     public TransactionDTO()
    {
    }

    public TransactionDTO(Transaction transaction)
    {
        this.day = transaction.getDay();
        this.month = transaction.getMonth();
        this.year = transaction.getYear();
//        this.date = String.valueOf(transaction.getDate());
        this.account = transaction.getAccount();
        this.amount = transaction.getAmount();
//        this.amount = String.valueOf(transaction.getAmount());
        this.category = transaction.getCategory();
        this.isPaid = transaction.getIsPaid();
        this.forWhom = transaction.getForWhom();
        this.description = transaction.getDescription();
    }
//
//
//     public TransactionDTO(String date, Account account, String amount, Category category, Payee isPaid, Payee forWhom, String description)
//    {
//        this.date = date;
//        this.account = account;
//        this.amount = amount;
//        this.category = category;
//        this.payee = payee;
//        this.gainer = gainer;
//        this.description = description;
//    }

    public TransactionDTO(int day, int month, int year, Account account, double amount, Category category, Payee isPaid, Payee forWhom, String description)
    {
        this.day = day;
        this.month = month;
        this.year = year;
        this.date = createDateString(day, month, year);
        this.account = account;
        this.amount = amount;
        this.category = category;
        this.isPaid = isPaid;
        this.forWhom = forWhom;
        this.description = description;
    }

//    public TransactionDTO(String date, Account account, double amount, Category category, Payee payee, Gainer gainer, String description)
//    {
//        this.date = date;
//        this.account = account;
//        this.amount = amount;
//        this.category = category;
//        this.payee = payee;
//        this.gainer = gainer;
//        this.description = description;
//    }

    public TransactionDTO(LocalDate date, Account account, double amount, Category category, Payee isPaid, Payee forWhom, String description)
    {
        this.date = String.valueOf(date);
//        this.date = date;
        this.account = account;
        this.amount = amount;
//        this.amount = String.valueOf(amount);
        this.category = category;
        this.isPaid = isPaid;
        this.forWhom = forWhom;
        this.description = description;
    }

     public TransactionDTO(LocalDate date, double amount, String description, int accountId, int categoryId, int payeeId, int gainerId)
    {
        this.date = String.valueOf(date);
        this.amount = amount;
//        this.amount = String.valueOf(amount);
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
        result.setDay(this.day);
        result.setMonth(this.month);
        result.setYear(this.year);
        result.setAccount(this.account);
        result.setAmount(this.amount);
//        result.setAmount(Double.parseDouble(this.amount));
        result.setCategory(this.category);
        result.setIsPaid(this.isPaid);
        result.setForWhom(this.forWhom);
        result.setDescription(this.description);
        result.setBudget(this.budget);

        return result;
    }

    String createDateString(int day, int month, int year)
    {
        String d = String.valueOf(day);

        if (day < 10)
            d = "0" + d;

        String m = String.valueOf(month);

        if (month < 10)
            m = "0" + m;

        return String.format("%s-%s-%s", year, m, d);
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

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

//
//    public String getAmount() {
//        return amount;
//    }
//
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }

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

    public Payee getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Payee isPaid) {
        this.isPaid = isPaid;
    }

    public Payee getForWhom() {
        return forWhom;
    }

    public void setForWhom(Payee forWhom) {
        this.forWhom = forWhom;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public BudgetPosition getBudgetPosition() {
        return budgetPosition;
    }

    public void setBudgetPosition(BudgetPosition budgetPosition) {
        this.budgetPosition = budgetPosition;
    }
}

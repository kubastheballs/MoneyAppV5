package com.moneyAppV5.transaction;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;
    private int day;
    private int month;
    private int year;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "payee_id")
    private Payee payee;
    @ManyToOne
    @JoinColumn(name = "gainer_id")
    private Gainer gainer;
    private String description;
    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
    @ManyToOne
    @JoinColumn(name = "budget_position_id")
    private BudgetPosition budgetPosition;

     public Transaction()
    {
    }

    public Transaction(TransactionDTO dto, int positionInt)
    {
//        TODO
    }

//    public Transaction(Transaction transaction, BudgetPosition position)
//    {
//        this.budgetPosition = position;
//    }

    public void updateFrom(final Transaction source)
    {
//        TODO
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDate getDate()
    {
        return transactionDate;
    }

    public void setDate(LocalDate date)
    {
        this.transactionDate = date;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Payee getPayee()
    {
        return payee;
    }

    public void setPayee(Payee payee)
    {
        this.payee = payee;
    }

    public Gainer getGainer()
    {
        return gainer;
    }

    public void setGainer(Gainer gainer)
    {
        this.gainer = gainer;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

//    public BudgetPosition getBudgetPosition()
//    {
//        return budgetPosition;
//    }
//
//    public void setBudgetPosition(BudgetPosition budgetPosition)
//    {
//        this.budgetPosition = budgetPosition;
//    }


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

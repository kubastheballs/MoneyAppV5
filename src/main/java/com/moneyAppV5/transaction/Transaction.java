package com.moneyAppV5.transaction;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.dto.TransactionDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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
    @JoinColumn(name = "is_paid_id")
    private Payee isPaid;
    @ManyToOne
    @JoinColumn(name = "for_whom_id")
    private Payee forWhom;
    private String description;
    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
    @ManyToOne
    @JoinColumn(name = "budget_position_id")
    private BudgetPosition budgetPosition;
    private Integer hash;

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

    @Override
    public int hashCode()
    {
        return Objects.hash(this.day, this.month, this.year, this.account, this.amount, this.category, this.isPaid, this.forWhom);
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

    //    public Payee getPayee()
//    {
//        return payee;
//    }
//
//    public void setPayee(Payee payee)
//    {
//        this.payee = payee;
//    }

//    public Gainer getGainer()
//    {
//        return gainer;
//    }
//
//    public void setGainer(Gainer gainer)
//    {
//        this.gainer = gainer;
//    }

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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }
}

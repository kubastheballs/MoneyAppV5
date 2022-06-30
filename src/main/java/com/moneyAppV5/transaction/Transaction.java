package com.moneyAppV5.transaction;

import com.moneyAppV5.bill.Bill;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private int day;
//    private int month;
//    private int year;
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
//    @ManyToOne
//    @JoinColumn(name = "is_paid_id")
//    private Payee isPaid;
    @ManyToOne
    @JoinColumn(name = "gainer_id")
    private Payee gainer;
    private String description;
//    @ManyToOne
//    @JoinColumn(name = "budget_id")
//    private Budget budget;
    @ManyToOne
    @JoinColumn(name = "budget_position_id")
    private BudgetPosition budgetPosition;
    private Integer hash;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

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

//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(this.day, this.month, this.year, this.account, this.amount, this.category, this.isPaid, this.forWhom);
//    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.amount, this.category, this.gainer, this.bill);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

//    public Account getAccount()
//    {
//        return account;
//    }
//
//    public void setAccount(Account account)
//    {
//        this.account = account;
//    }

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

//    public Payee getIsPaid() {
//        return isPaid;
//    }
//
//    public void setIsPaid(Payee isPaid) {
//        this.isPaid = isPaid;
//    }

    public Payee getGainer() {
        return gainer;
    }

    public void setGainer(Payee forWhom) {
        this.gainer = forWhom;
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


//    public int getDay() {
//        return day;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }
//
//    public int getMonth() {
//        return month;
//    }
//
//    public void setMonth(int month) {
//        this.month = month;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    public Budget getBudget() {
//        return budget;
//    }
//
//    public void setBudget(Budget budget) {
//        this.budget = budget;
//    }

    public BudgetPosition getBudgetPosition() {
        return budgetPosition;
    }

    public void setBudgetPosition(BudgetPosition budgetPosition) {
        this.budgetPosition = budgetPosition;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}

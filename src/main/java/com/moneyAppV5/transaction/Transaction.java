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

    private double amount;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "gainer_id")
    private Payee gainer;
    private String description;

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

    public void updateFrom(final Transaction source)
    {
//        TODO
    }

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

    public TransactionDTO toDto()
    {
        return new TransactionDTO.TransactionDtoBuilder()
                .buildDate(this.bill.getDay(), this.bill.getBudget().getMonth(), this.bill.getBudget().getYear())
                .buildAccount(this.bill.getAccount().toDto())
                .buildAmount(this.amount)
                .buildCategory(this.category.toDto())
                .buildPayee(this.bill.getPayee().toDto())
                .buildGainer(this.gainer.toDto())
                .buildDescription(this.description)
                .buildHash(this.hash)
                .buildBudget(this.bill.getBudget().toDto())
                .buildBudgetPosition(this.budgetPosition.toDto())
                .build();
    }
}

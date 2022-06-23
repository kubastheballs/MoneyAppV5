package com.moneyAppV5.budget;

import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.transaction.Transaction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "budget_positions")
public
class BudgetPosition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
    @OneToMany(mappedBy = "budgetPosition")
    private Set<Transaction> transactions;
    private double plannedAmount;
    private String description;
    private Integer hash;

     public BudgetPosition()
    {
    }

    public BudgetPosition(Category category, Budget budget)
    {
        this.category = category;
        this.budget = budget;
        this.hash = hashCode();
    }

    public BudgetPositionDTO toDto()
    {
        return new BudgetPositionDTO.BudgetPositionDtoBuilder()
                .buildCategory(this.category.toDto())
                .buildPlannedAmount(this.plannedAmount)
                .buildDescription(this.description)
                .buildHash(this.hash)
                .build();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.category, this.budget);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Budget getBudget()
    {
        return budget;
    }

    public void setBudget(Budget budget)
    {
        this.budget = budget;
    }

    public Set<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    public double getPlannedAmount()
    {
        return plannedAmount;
    }

    public void setPlannedAmount(double plannedAmount)
    {
        this.plannedAmount = plannedAmount;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }
}

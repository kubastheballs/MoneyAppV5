package com.moneyAppV5.category;

import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.transaction.Transaction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "main_category_id")
    private MainCategory mainCategory;
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<BudgetPosition> budgetPositions;

     public Category()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public MainCategory getMainCategory()
    {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory)
    {
        this.mainCategory = mainCategory;
    }

    public SubCategory getSubCategory()
    {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory)
    {
        this.subCategory = subCategory;
    }

    public Set<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<BudgetPosition> getBudgetPositions()
    {
        return budgetPositions;
    }

    public void setBudgetPositions(Set<BudgetPosition> budgetPositions)
    {
        this.budgetPositions = budgetPositions;
    }
}

package com.moneyAppV5.transaction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "payees")
public class Payee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String payee;
    private String description;
    @OneToMany(mappedBy = "payee")
    private Set<Transaction> transactions;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPayee()
    {
        return payee;
    }

    public void setPayee(String payee)
    {
        this.payee = payee;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions)
    {
        this.transactions = transactions;
    }
}

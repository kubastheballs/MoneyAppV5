package com.moneyAppV5.transaction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gainers")
public
class Gainer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String gainer;
    private String description;
    @OneToMany(mappedBy = "gainer")
    private Set<Transaction> transactions;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getGainer()
    {
        return gainer;
    }

    public void setGainer(String gainer)
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

    public Set<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions)
    {
        this.transactions = transactions;
    }
}

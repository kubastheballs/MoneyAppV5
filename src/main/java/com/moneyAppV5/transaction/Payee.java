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
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String description;
    @OneToMany(mappedBy = "isPaid")
    private Set<Transaction> isPaidTransactions;
    @OneToMany(mappedBy = "forWhom")
    private Set<Transaction> forWhomTransactions;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String payee)
    {
        this.name = payee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<Transaction> getIsPaidTransactions()
    {
        return isPaidTransactions;
    }

    public void setIsPaidTransactions(Set<Transaction> transactions)
    {
        this.isPaidTransactions = transactions;
    }

    public Set<Transaction> getForWhomTransactions() {
        return forWhomTransactions;
    }

    public void setForWhomTransactions(Set<Transaction> forWhomTransactions) {
        this.forWhomTransactions = forWhomTransactions;
    }
}

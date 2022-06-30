package com.moneyAppV5.transaction;

import com.moneyAppV5.transaction.dto.PayeeDTO;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
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
    private Integer hash;

    @Override
    public int hashCode()
    {
        return Objects.hash(this.name, this.role);
    }


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

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public PayeeDTO toDto()
    {
        var payee = new PayeeDTO();
        payee.setName(this.name);
        payee.setRole(this.role);
        payee.setDescription(this.description);
//        TODO - podział na dwie listy czy złączenie?
//        payee.setTransactionsDto(this.transactions);
        payee.setHash(this.hash);

        return payee;
    }
}

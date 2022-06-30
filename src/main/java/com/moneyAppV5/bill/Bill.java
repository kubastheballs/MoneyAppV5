package com.moneyAppV5.bill;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
  @Table(name = "bills")
public class Bill
{
//    TODO placeholder dla rachunku (do przejścia na model rachunek - transakcje)
//    TODO czy rachunek powinien mieć przypisany typ? bo to albo wydatki albo dochody
//    albo przynajmniej zaznaczenie w html i wtedy sortowanie kategorii tylko dla danego typiu
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int day;
    @ManyToOne
    @JoinColumn(name = "payee_id")
    private Payee payee;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
    @OneToMany(mappedBy = "bill")
    private Set<Transaction> transactions;
    private int hash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.day, this.payee, this.account, this.budget);
    }
}

package com.moneyAppV5.bill;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;


import javax.persistence.*;
import java.util.Set;

@Entity
  @Table(name = "bills")
public class Bill
{
//    TODO placeholder dla rachunku (do przejścia na model rachunek - transakcje)
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

}

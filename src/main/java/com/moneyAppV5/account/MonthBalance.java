package com.moneyAppV5.account;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="month_balances")
class MonthBalance
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate balanceDate;
    private double balance;
    @ManyToOne
    @JoinColumn
    private Account account;

}

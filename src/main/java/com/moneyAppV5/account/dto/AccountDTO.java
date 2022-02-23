package com.moneyAppV5.account.dto;

import com.moneyAppV5.account.Account;

import java.time.LocalDate;

public class AccountDTO
{
    private String name;
    private String description;
    private LocalDate deadline;
    private double target;
    private double actualBalance;

    public AccountDTO()
    {
    }

    public AccountDTO(String name, double actualBalance, String description)
    {
        this.name = name;
        this.actualBalance = actualBalance;
        this.description = description;
    }

    public Account toAccount()
    {
        return new Account(this.name, this.actualBalance);
    }
}

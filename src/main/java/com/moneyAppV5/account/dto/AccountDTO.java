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

    public AccountDTO(String name, double actualBalance) {
        this.name = name;
        this.actualBalance = actualBalance;
    }

    public AccountDTO(String name, double actualBalance, String description)
    {
        this.name = name;
        this.actualBalance = actualBalance;
        this.description = description;
    }

    public AccountDTO(Account account)
    {
        this.name = account.getName();
        this.actualBalance = account.getActualBalance();
        this.description = account.getDescription();
    }

    public Account toAccount()
    {
        var result = new Account();

        result.setName(this.name);
        result.setActualBalance(this.actualBalance);
        result.setDescription(this.description);

        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(double actualBalance) {
        this.actualBalance = actualBalance;
    }

    public String toDisplay()
    {
        return String.format("%s: %s [zł]", this.name, this.actualBalance);
    }
}

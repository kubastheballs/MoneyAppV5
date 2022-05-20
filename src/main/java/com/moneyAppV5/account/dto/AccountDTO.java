package com.moneyAppV5.account.dto;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountDTO
{
    private String name;
    private String description;
    private LocalDate deadline;
    private double target;
    private double actualBalance;
    private List<TransactionDTO> transactions;
    private Integer hash;

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
        if (account.getTransactions() != null)
            this.transactions = transactionsToDto(account.getTransactions());
        this.hash = account.getHash();
    }

    private List<TransactionDTO> transactionsToDto(Set<Transaction> list)
    {
        List<TransactionDTO> dtos = new ArrayList<>();

        for (Transaction t : list)
            dtos.add(new TransactionDTO(t));

        return dtos;
    }


    public Account toAccount()
    {
        var result = new Account();

        result.setName(this.name);
        result.setActualBalance(this.actualBalance);
        result.setDescription(this.description);
        if (this.transactions != null)
            result.setTransactions(dtoToTransactions(this.transactions));
        result.setHash(result.hashCode());

        return result;
    }

    Set<Transaction> dtoToTransactions(List<TransactionDTO> dtos)
    {
        Set<Transaction> transactions = new HashSet<>();

        for (TransactionDTO t : dtos)
        transactions.add(t.toTransaction());

        return transactions;
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

    public List<TransactionDTO> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions)
    {
        this.transactions = transactions;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public String toDisplay()
    {
        return String.format("%s: %s [zł]", this.name, this.actualBalance);
    }
}

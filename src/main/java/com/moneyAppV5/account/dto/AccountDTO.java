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
    private double actualMonthIncome;
    private double actualMonthExpense;
    private double actualMonthBalance;
    private double actualMonthMinusOneIncome;
    private double actualMonthMinusOneExpense;
    private double actualMonthMinusOneBalance;
    private double actualMonthMinusTwoIncome;
    private double actualMonthMinusTwoExpense;
    private double actualMonthMinusTwoBalance;
    private double actualYearIncome;
    private double actualYearExpense;
    private double actualYearBalance;
    private double overallIncome;
    private double overallExpense;
    private double overallBalance;

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
//        if (account.getTransactions() != null)
//            this.transactions = transactionsToDto(account.getTransactions());
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getActualMonthIncome() {
        return actualMonthIncome;
    }

    public void setActualMonthIncome(double actualMonthIncome) {
        this.actualMonthIncome = actualMonthIncome;
    }

    public double getActualMonthExpense() {
        return actualMonthExpense;
    }

    public void setActualMonthExpense(double actualMonthExpense) {
        this.actualMonthExpense = actualMonthExpense;
    }

    public double getActualMonthBalance() {
        return actualMonthBalance;
    }

    public void setActualMonthBalance(double actualMonthBalance) {
        this.actualMonthBalance = actualMonthBalance;
    }

    public double getActualMonthMinusOneIncome() {
        return actualMonthMinusOneIncome;
    }

    public void setActualMonthMinusOneIncome(double actualMonthMinusOneIncome) {
        this.actualMonthMinusOneIncome = actualMonthMinusOneIncome;
    }

    public double getActualMonthMinusOneExpense() {
        return actualMonthMinusOneExpense;
    }

    public void setActualMonthMinusOneExpense(double actualMonthMinusOneExpense) {
        this.actualMonthMinusOneExpense = actualMonthMinusOneExpense;
    }

    public double getActualMonthMinusOneBalance() {
        return actualMonthMinusOneBalance;
    }

    public void setActualMonthMinusOneBalance(double actualMonthMinusOneBalance) {
        this.actualMonthMinusOneBalance = actualMonthMinusOneBalance;
    }

    public double getActualMonthMinusTwoIncome() {
        return actualMonthMinusTwoIncome;
    }

    public void setActualMonthMinusTwoIncome(double actualMonthMinusTwoIncome) {
        this.actualMonthMinusTwoIncome = actualMonthMinusTwoIncome;
    }

    public double getActualMonthMinusTwoExpense() {
        return actualMonthMinusTwoExpense;
    }

    public void setActualMonthMinusTwoExpense(double actualMonthMinusTwoExpense) {
        this.actualMonthMinusTwoExpense = actualMonthMinusTwoExpense;
    }

    public double getActualMonthMinusTwoBalance() {
        return actualMonthMinusTwoBalance;
    }

    public void setActualMonthMinusTwoBalance(double actualMonthMinusTwoBalance) {
        this.actualMonthMinusTwoBalance = actualMonthMinusTwoBalance;
    }

    public double getActualYearIncome() {
        return actualYearIncome;
    }

    public void setActualYearIncome(double actualYearIncome) {
        this.actualYearIncome = actualYearIncome;
    }

    public double getActualYearExpense() {
        return actualYearExpense;
    }

    public void setActualYearExpense(double actualYearExpense) {
        this.actualYearExpense = actualYearExpense;
    }

    public double getActualYearBalance() {
        return actualYearBalance;
    }

    public void setActualYearBalance(double actualYearBalance) {
        this.actualYearBalance = actualYearBalance;
    }

    public double getOverallIncome() {
        return overallIncome;
    }

    public void setOverallIncome(double overallIncome) {
        this.overallIncome = overallIncome;
    }

    public double getOverallExpense() {
        return overallExpense;
    }

    public void setOverallExpense(double overallExpense) {
        this.overallExpense = overallExpense;
    }

    public double getOverallBalance() {
        return overallBalance;
    }

    public void setOverallBalance(double overallBalance) {
        this.overallBalance = overallBalance;
    }
}

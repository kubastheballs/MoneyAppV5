package com.moneyAppV5.account.dto;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.bill.Bill;
import com.moneyAppV5.bill.dto.BillDTO;
import com.moneyAppV5.budget.dto.BudgetDTO;
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
    private List<BillDTO> bills;
    private List<TransactionDTO> transactions;
    private BudgetDTO actualMonthBudget;
    private BudgetDTO actualMonthMinusOneBudget;
    private BudgetDTO actualMonthMinusTwoBudget;
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

    private AccountDTO(AccountDtoBuilder builder)
    {
        this.name = builder.name;
        this.description = builder.description;
        this.deadline = builder.deadline;
        this.target = builder.target;
        this.actualBalance = builder.actualBalance;
        this.bills = builder.bills;
        this.actualMonthBudget = builder.actualMonthBudget;
        this.actualMonthMinusOneBudget = builder.actualMonthMinusOneBudget;
        this.actualMonthMinusTwoBudget = builder.actualMonthMinusTwoBudget;
        this.actualMonthIncome = builder.actualMonthIncome;
        this.actualMonthExpense = builder.actualMonthExpense;
        this.actualMonthBalance = builder.actualMonthBalance;
        this.actualMonthMinusOneIncome = builder.actualMonthMinusOneIncome;
        this.actualMonthMinusOneExpense = builder.actualMonthMinusOneExpense;
        this.actualMonthMinusOneBalance = builder.actualMonthMinusOneBalance;
        this.actualMonthMinusTwoIncome = builder.actualMonthMinusTwoIncome;
        this.actualMonthMinusTwoExpense = builder.actualMonthMinusTwoExpense;
        this.actualMonthMinusTwoBalance = builder.actualMonthMinusTwoBalance;
        this.actualYearIncome = builder.actualYearIncome;
        this.actualYearExpense = builder.actualYearExpense;
        this.actualYearBalance = builder.actualYearBalance;
        this.overallIncome = builder.overallIncome;
        this.overallExpense = builder.overallExpense;
        this.overallBalance = builder.overallBalance;
        this.hash = builder.hash;
    }


    public Account toAccount()
    {
        var result = new Account();

        result.setName(this.name);
        result.setActualBalance(this.actualBalance);
        result.setDescription(this.description);
        if (this.bills != null)
            result.setBills(toBills(this.bills));
        result.setHash(result.hashCode());

        return result;
    }

    Set<Bill> toBills(List<BillDTO> list)
    {
        Set<Bill> bills = new HashSet<>();

        for (BillDTO b : list)
        bills.add(b.toBill());

        return bills;
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

    public List<BillDTO> getBills()
    {
        return bills;
    }

    public void setBills(List<BillDTO> bills)
    {
        this.bills = bills;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
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

    public BudgetDTO getActualMonthBudget() {
        return actualMonthBudget;
    }

    public void setActualMonthBudget(BudgetDTO actualMonthBudget) {
        this.actualMonthBudget = actualMonthBudget;
    }

    public BudgetDTO getActualMonthMinusOneBudget() {
        return actualMonthMinusOneBudget;
    }

    public void setActualMonthMinusOneBudget(BudgetDTO actualMonthMinusOneBudget) {
        this.actualMonthMinusOneBudget = actualMonthMinusOneBudget;
    }

    public BudgetDTO getActualMonthMinusTwoBudget() {
        return actualMonthMinusTwoBudget;
    }

    public void setActualMonthMinusTwoBudget(BudgetDTO actualMonthMinusTwoBudget) {
        this.actualMonthMinusTwoBudget = actualMonthMinusTwoBudget;
    }

    public static class AccountDtoBuilder
    {
        private String name;
        private String description;
        private LocalDate deadline;
        private double target;
        private double actualBalance;
        private List<BillDTO> bills;
        private List<TransactionDTO> transactions;
        private BudgetDTO actualMonthBudget;
        private BudgetDTO actualMonthMinusOneBudget;
        private BudgetDTO actualMonthMinusTwoBudget;
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
        private int hash;

        public AccountDtoBuilder buildName(String name)
        {
            this.name = name;

            return this;
        }

        public AccountDtoBuilder buildDescription(String description)
        {
            this.description = description;

            return this;
        }

        public AccountDtoBuilder buildDeadline(LocalDate deadline)
        {
            this.deadline = deadline;

            return this;
        }

        public AccountDtoBuilder buildTarget(double target)
        {
            this.target = target;

            return this;
        }

        public AccountDtoBuilder buildActualBalance(double actualBalance)
        {
            this.actualBalance = actualBalance;

            return this;
        }

        public AccountDtoBuilder buildBills(List<BillDTO> bills)
        {
            this.bills = bills;

            return this;
        }

        public AccountDtoBuilder buildTransactions(List<TransactionDTO> transactions)
        {
            this.transactions = transactions;

            return this;
        }

        public AccountDtoBuilder buildActualMonthBudget(BudgetDTO actualMonthBudget)
        {
            this.actualMonthBudget = actualMonthBudget;
            
            this.actualMonthIncome = this.actualMonthBudget.getActualIncomes();
            this.actualMonthExpense = this.actualMonthBudget.getActualExpenses();
            this.actualMonthBalance = this.actualMonthIncome - this.actualMonthExpense;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusOneBudget(BudgetDTO actualMonthMinusOneBudget)
        {
            this.actualMonthMinusOneBudget = actualMonthMinusOneBudget;

            this.actualMonthMinusOneIncome = this.actualMonthMinusOneBudget.getActualIncomes();
            this.actualMonthMinusOneExpense = this.actualMonthMinusOneBudget.getActualExpenses();
            this.actualMonthMinusOneBalance = this.actualMonthMinusOneIncome - this.actualMonthMinusOneExpense;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusTwoBudget(BudgetDTO actualMonthMinusTwoBudget)
        {
            this.actualMonthMinusTwoBudget = actualMonthMinusTwoBudget;

            this.actualMonthMinusTwoIncome = this.actualMonthMinusTwoBudget.getActualIncomes();
            this.actualMonthMinusTwoExpense = this.actualMonthMinusTwoBudget.getActualExpenses();
            this.actualMonthMinusTwoBalance = this.actualMonthMinusTwoIncome - this.actualMonthMinusTwoExpense;

            return this;
        }
        

        public AccountDtoBuilder buildActualMonthIncome(double income)
        {
            this.actualMonthIncome = income;

            return this;
        }

        public AccountDtoBuilder buildActualMonthIncome()
        {
            this.actualMonthIncome = this.actualMonthBudget.getActualIncomes();

            return this;
        }

        public AccountDtoBuilder buildActualMonthExpense(double expense)
        {
            this.actualMonthExpense = expense;

            return this;
        }

        public AccountDtoBuilder buildActualMonthExpense()
        {
            this.actualMonthExpense = this.actualMonthBudget.getActualExpenses();

            return this;
        }

        public AccountDtoBuilder buildActualMonthBalance(double balance)
        {
            this.actualMonthBalance = balance;

            return this;
        }

        public AccountDtoBuilder buildActualMonthBalance()
        {
            this.actualMonthBalance = this.actualMonthIncome - this.actualMonthExpense;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusOneIncome(double income)
        {
            this.actualMonthMinusOneIncome = income;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusOneIncome()
        {
            this.actualMonthMinusOneIncome = this.actualMonthMinusOneBudget.getActualIncomes();

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusOneExpense(double expense)
        {
            this.actualMonthMinusOneExpense = expense;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusOneExpense()
        {
            this.actualMonthMinusOneExpense = this.actualMonthMinusOneBudget.getActualExpenses();

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusOneBalance(double balance)
        {
            this.actualMonthMinusOneBalance = balance;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusOneBalance()
        {
            this.actualMonthMinusOneBalance = this.actualMonthMinusOneIncome - this.actualMonthMinusOneExpense;

            return this;
        }


        public AccountDtoBuilder buildActualMonthMinusTwoIncome(double income)
        {
            this.actualMonthMinusTwoIncome = income;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusTwoIncome()
        {
            this.actualMonthMinusTwoIncome = this.actualMonthMinusTwoBudget.getActualIncomes();

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusTwoExpense(double expense)
        {
            this.actualMonthMinusTwoExpense = expense;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusTwoExpense()
        {
            this.actualMonthMinusTwoExpense = this.actualMonthMinusTwoBudget.getActualExpenses();

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusTwoBalance(double balance)
        {
            this.actualMonthMinusTwoBalance = balance;

            return this;
        }

        public AccountDtoBuilder buildActualMonthMinusTwoBalance()
        {
            this.actualMonthMinusTwoBalance = this.actualMonthMinusTwoIncome - this.actualMonthMinusTwoExpense;

            return this;
        }


        public AccountDtoBuilder buildActualYearIncome(double income)
        {
            this.actualYearIncome = income;

            return this;
        }

        public AccountDtoBuilder buildActualYearExpense(double expense)
        {
            this.actualYearExpense = expense;

            return this;
        }

        public AccountDtoBuilder buildActualYearBalance(double balance)
        {
            this.actualYearBalance = balance;

            return this;
        }

        public AccountDtoBuilder buildActualYearBalance()
        {
            this.actualYearBalance = this.actualYearIncome - this.actualYearExpense;

            return this;
        }

        public AccountDtoBuilder buildOverallIncome(double income)
        {
            this.overallIncome = income;

            return this;
        }

        public AccountDtoBuilder buildOverallExpense(double expense)
        {
            this.overallExpense = expense;

            return this;
        }

        public AccountDtoBuilder buildOverallBalance(double balance)
        {
            this.overallBalance = balance;

            return this;
        }

        public AccountDtoBuilder buildOverallBalance()
        {
            this.overallBalance = this.overallIncome - this.overallExpense;

            return this;
        }

        public AccountDtoBuilder buildHash(int hash)
        {
            this.hash = hash;

            return this;
        }

        public AccountDTO build()
        {
            return new AccountDTO(this);
        }
    }
}

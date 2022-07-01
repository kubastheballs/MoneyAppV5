package com.moneyAppV5.bill.dto;

import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.bill.Bill;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.category.dto.CategoryDTO;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.PayeeDTO;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BillDTO
{
    private int day;
    private PayeeDTO payee;
    private AccountDTO account;
    private BudgetDTO budget;
    private List<TransactionDTO> transactions;
    private int hash;
    private double sum;

    public BillDTO()
    {
    }
    
    public BillDTO(Bill bill)
    {
        this.day = bill.getDay();
        this.payee = bill.getPayee().toDto();
        this.account = bill.getAccount().toDto();
        this.budget = bill.getBudget().toDto();
        this.transactions = bill.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toList());
        this.hash = bill.getHash();
        this.sum = sumTransactions();
    }

    private BillDTO(BillDtoBuilder builder)
    {
        this.day = builder.day;
        this.payee = builder.payee;
        this.account = builder.account;
        this.budget = builder.budget;
        this.transactions = builder.transactions;
        this.hash = builder.hash;
        this.sum = builder.sum;
    }

    public Bill toBill()
    {
        var bill = new Bill();

        bill.setPayee(this.payee.toPayee());
        bill.setAccount(this.account.toAccount());
        bill.setBudget(this.budget.toBudget());
        bill.setTransactions(toTransactions(this.transactions));
        bill.setHash(this.hash);

        return bill;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public PayeeDTO getPayee() {
        return payee;
    }

    public void setPayee(PayeeDTO payee) {
        this.payee = payee;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public BudgetDTO getBudget() {
        return budget;
    }

    public void setBudget(BudgetDTO budget) {
        this.budget = budget;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double sumTransactions()
    {
        double sum = 0;

        for (TransactionDTO t : this.transactions)
        {
            if ((t.getCategory().getType()).equals(Type.EXPENSE))
                sum -= t.getAmount();
            else
                sum += t.getAmount();
        }

        return sum;
    }

    public List<CategoryDTO> getCategories()
    {
        var categories = new ArrayList<CategoryDTO>();

        for (TransactionDTO t : this.transactions)
            categories.add(t.getCategory());

        return categories;
    }

    private Set<Transaction> toTransactions(List<TransactionDTO> transactions)
    {
        var result = new HashSet<Transaction>();

        for (TransactionDTO t : transactions)
            result.add(t.toTransaction());

        return result;
    }

    public static class BillDtoBuilder
    {
        private int day;
        private PayeeDTO payee;
        private AccountDTO account;
        private BudgetDTO budget;
        private List<TransactionDTO> transactions;
        private int hash;
        private double sum;

        public BillDtoBuilder buildDay(int day)
        {
            this.day = day;

            return this;
        }

        public BillDtoBuilder buildPayee(PayeeDTO payee)
        {
            this.payee = payee;

            return this;
        }

        public BillDtoBuilder buildAccount(AccountDTO account)
        {
            this.account = account;

            return this;
        }

        public BillDtoBuilder buildBudget(BudgetDTO budget)
        {
            this.budget = budget;

            return this;
        }

        public BillDtoBuilder buildTransactions(List<TransactionDTO> transactions)
        {
            this.transactions = transactions;

            return this;
        }

        public BillDtoBuilder buildHash(int hash)
        {
            this.hash = hash;

            return this;
        }

        public BillDtoBuilder buildSum(double sum)
        {
            this.sum = sum;

            return this;
        }
//TODO czy tak naprawdę to jest potrzebne?
        public BillDtoBuilder buildSum(List<TransactionDTO> transactions)
        {
            double sum = 0;

            for (TransactionDTO t : transactions)
            {
                if ((t.getCategory().getType()).equals(Type.EXPENSE))
                    sum -= t.getAmount();
                else
                    sum += t.getAmount();
            }

            return this;
        }

        public BillDtoBuilder buildSum()
        {
            double sum = 0;

            for (TransactionDTO t : this.transactions)
            {
                if ((t.getCategory().getType()).equals(Type.EXPENSE))
                    sum -= t.getAmount();
                else
                    sum += t.getAmount();
            }

            return this;
        }

        public BillDTO build()
        {
            return new BillDTO(this);
        }
    }
}

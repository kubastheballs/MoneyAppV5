package com.moneyAppV5.budget.dto;

import com.moneyAppV5.bill.dto.BillDTO;
import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BudgetDTO
{
    private int month;
    private int year;
    private String monthName;
    private String name;
    private double plannedIncomes;
    private double actualIncomes;
    private double balanceIncomes;
    private double plannedExpenses;
    private double actualExpenses;
    private double balanceExpenses;
    private double balancePlanned;
    private double balanceActual;

    private List<BudgetPositionDTO> incomesDto;
    private List<BudgetPositionDTO> expensesDto;
    private List<TransactionDTO> transactionsDto;
    private List<BillDTO> billsDto;

    private String description;
    private Integer hash;

    public BudgetDTO() {
    }

    public BudgetDTO(int month, int year) {
        this.month = month;
        this.year = year;
        setMonthName(month);
        setName(month, year);
    }

    BudgetDTO(int month, int year, String description) {
        this.month = month;
        this.year = year;
        setMonthName(month);
        setName(month, year);
        this.description = description;
    }


    public BudgetDTO(Budget budget) {
        this.month = budget.getMonth();
        this.year = budget.getYear();
        setMonthName(budget.getMonth());
        setName(budget.getMonth(), budget.getYear());
        this.description = budget.getDescription();
        this.hash = budget.getHash();
    }

    private BudgetDTO(BudgetDtoBuilder builder)
    {
        this.month = builder.month;
        this.year = builder.year;
        this.monthName = builder.monthName;
        this.name = builder.name;
        this.plannedIncomes = builder.plannedIncomes;
        this.actualIncomes = builder.actualIncomes;
        this.balanceIncomes = builder.balanceIncomes;
        this.plannedExpenses = builder.plannedExpenses;
        this.actualExpenses = builder.actualExpenses;
        this.balanceExpenses = builder.balanceExpenses;
        this.balancePlanned = builder.balancePlanned;
        this.balanceActual = builder.balanceActual;

        this.incomesDto = builder.incomesDto;
        this.expensesDto = builder.expensesDto;
        this.transactionsDto = builder.transactionsDto;
        this.billsDto = builder.billsDto;
        this.description = builder.description;
        this.hash = builder.hash;
    }

    public Budget toBudget()
    {
        return new Budget.BudgetBuilder()
                .buildMonth(this.month)
                .buildYear(this.year)
                .buildDescription(this.description)
                .buildHash(this.hash)
                .build();
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonthName() {
        return monthName;
    }
//TODO polskie nazwy miesięcy
    public void setMonthName(int month) {
        this.monthName = Month.of(month).toString();
    }

    public String getName() {
        return name;
    }

    public void setName(int month, int year) {
        this.name = String.format("%s/%s", month, year);
    }

    //    public Month getMonth() {
//        return month;
//    }
//
//    public void setMonth(Month month) {
//        this.month = month;
//    }
//
//    public Year getYear() {
//        return year;
//    }
//
//    public void setYear(Year year) {
//        this.year = year;
//    }



//    public Set<BudgetPosition> getIncomes() {
//        return incomes;
//    }
//
//    public void setIncomes(Set<BudgetPosition> incomes) {
//        this.incomes = incomes;
//    }
//
//    public Set<BudgetPosition> getExpenses() {
//        return expenses;
//    }
//
//    public void setExpenses(Set<BudgetPosition> expenses) {
//        this.expenses = expenses;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPlannedIncomes() {
        return plannedIncomes;
    }

    public void setPlannedIncomes(double plannedIncomes) {
        this.plannedIncomes = plannedIncomes;
    }

    public double getActualIncomes() {
        return actualIncomes;
    }

    public void setActualIncomes(double actualIncomes) {
        this.actualIncomes = actualIncomes;
    }

    public double getBalanceIncomes() {
        return balanceIncomes;
    }

    public void setBalanceIncomes(double balanceIncomes) {
        this.balanceIncomes = balanceIncomes;
    }

    public double getPlannedExpenses() {
        return plannedExpenses;
    }

    public void setPlannedExpenses(double plannedExpenses) {
        this.plannedExpenses = plannedExpenses;
    }

    public double getActualExpenses() {
        return actualExpenses;
    }

    public void setActualExpenses(double actualExpenses) {
        this.actualExpenses = actualExpenses;
    }

    public double getBalanceExpenses() {
        return balanceExpenses;
    }

    public void setBalanceExpenses(double balanceExpenses) {
        this.balanceExpenses = balanceExpenses;
    }

    public double getBalancePlanned() {
        return balancePlanned;
    }

    public void setBalancePlanned(double balancePlanned) {
        this.balancePlanned = balancePlanned;
    }

    public double getBalanceActual() {
        return balanceActual;
    }

    public void setBalanceActual(double balanceActual) {
        this.balanceActual = balanceActual;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public List<BudgetPositionDTO> getIncomesDto() {
        return incomesDto;
    }

    public void setIncomesDto(List<BudgetPositionDTO> incomesDto) {
        this.incomesDto = incomesDto;
    }

    public List<BudgetPositionDTO> getExpensesDto() {
        return expensesDto;
    }

    public void setExpensesDto(List<BudgetPositionDTO> expensesDto) {
        this.expensesDto = expensesDto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TransactionDTO> getTransactionsDto() {
        return transactionsDto;
    }

    public void setTransactionsDto(List<TransactionDTO> transactionsDto) {
        this.transactionsDto = transactionsDto;
    }

    public static class BudgetDtoBuilder
    {
        private int month;
        private int year;
        private String monthName;
        private String name;
        private double plannedIncomes;
        private double actualIncomes;
        private double balanceIncomes;
        private double plannedExpenses;
        private double actualExpenses;
        private double balanceExpenses;
        private double balancePlanned;
        private double balanceActual;

        private List<BudgetPositionDTO> incomesDto;
        private List<BudgetPositionDTO> expensesDto;
        private List<TransactionDTO> transactionsDto;
        private List<BillDTO> billsDto;
        private String description;
        private Integer hash;

        public BudgetDtoBuilder buildMonth(int month)
        {
            this.month = month;

            return this;
        }

        public BudgetDtoBuilder buildYear(int year)
        {
            this.year = year;

            return this;
        }

        public BudgetDtoBuilder buildMonthName()
        {
            this.monthName = Month.of(this.month).toString();

            return this;
        }

        public BudgetDtoBuilder buildName()
        {
            this.name = String.format("%s/%s", this.month, this.year);

            return this;
        }

        public BudgetDtoBuilder buildPlannedIncomes(double plannedIncomes)
        {
            this.plannedIncomes = plannedIncomes;

            return this;
        }

        public BudgetDtoBuilder buildActualIncomes(double actualIncomes)
        {
            this.actualIncomes = actualIncomes;

            return this;
        }

        public BudgetDtoBuilder buildBalanceIncomes(double balanceIncomes)
        {
            this.balanceIncomes = balanceIncomes;

            return this;
        }

        public BudgetDtoBuilder buildPlannedExpenses(double plannedExpenses)
        {
            this.plannedExpenses = plannedExpenses;

            return this;
        }

        public BudgetDtoBuilder buildActualExpenses(double actualExpenses)
        {
            this.actualExpenses = actualExpenses;

            return this;
        }

        public BudgetDtoBuilder buildBalanceExpenses(double balanceExpenses)
        {
            this.balanceExpenses = balanceExpenses;

            return this;
        }

        public BudgetDtoBuilder buildBalancePlanned(double balancePlanned)
        {
            this.balancePlanned = balancePlanned;
        
            return this;
        }

        public BudgetDtoBuilder buildBalanceActual(double balanceActual)
        {
            this.balanceActual = balanceActual;
        
            return this;
        }

        public BudgetDtoBuilder buildIncomesDto(List<BudgetPositionDTO> incomesDto)
        {
            this.incomesDto = incomesDto;
        
            return this;
        }

        public BudgetDtoBuilder buildExpensesDto(List<BudgetPositionDTO> expensesDto)
        {
            this.expensesDto = expensesDto;
        
            return this;
        }

        public BudgetDtoBuilder buildBillsDto(List<BillDTO> billsDto)
        {
            this.billsDto = billsDto;

            return this;
        }

        public BudgetDtoBuilder buildTransactionsDto(List<TransactionDTO> transactionsDto)
        {
            this.transactionsDto = transactionsDto;

            return this;
        }

        public BudgetDtoBuilder buildTransactionsDto()
        {
            var transactions = new ArrayList<TransactionDTO>();

            for (BillDTO b : this.billsDto)
                transactions.addAll(b.getTransactions());

            this.transactionsDto = transactions;

            return this;
        }

        public BudgetDtoBuilder buildTransactionsDtoFromBillsDto(List<BillDTO> billsDto)
        {
            for (BillDTO b : this.billsDto)
                this.transactionsDto.addAll(b.getTransactions());

            return this;
        }

        public BudgetDtoBuilder buildDescription(String description)
        {
            this.description = description;

            return this;
        }

        public BudgetDtoBuilder buildHash(Integer hash)
        {
            this.hash = hash;

            return this;
        }

        public BudgetDTO build()
        {
            return new BudgetDTO(this);
        }
    }
}

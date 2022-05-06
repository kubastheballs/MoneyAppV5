package com.moneyAppV5.budget.dto;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BudgetDTO
{
    private int month;
    private int year;
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
    private List<BudgetPosition> incomes;
    private List<BudgetPosition> expenses;
    private String description;
    private Integer hash;

     public BudgetDTO()
    {
    }

     public BudgetDTO(int month, int year)
    {
        this.month = month;
        this.year = year;
        setName(month, year);
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    BudgetDTO(int month, int year, String description)
    {
        this.month = month;
        this.year = year;
        setName(month, year);
        this.description = description;
    }

    BudgetDTO(int month, int year, List<BudgetPosition> incomes, List<BudgetPosition> expenses, String description)
    {
        this.month = month;
        this.year = year;
        setName(month, year);
        this.incomes = incomes;
        this.expenses = expenses;
        this.description = description;
    }

     public BudgetDTO(Budget budget)
    {
        this.month = budget.getMonth();
        this.year = budget.getYear();
        setName(budget.getMonth(), budget.getYear());
        this.description = budget.getDescription();
        this.hash = budget.getHash();
    }

    public Budget toBudget()
    {
        var result = new Budget();
        result.setMonth(this.month);
        result.setYear(this.year);
//        result.setIncomes(new HashSet<>(this.incomes));
//        result.setExpenses(new HashSet<>(this.expenses));
        result.setDescription(this.description);
        result.setHash(result.hashCode());

        return result;
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

    public String getName() {
        return name;
    }

    public void setName(int month, int year)
    {
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

    public List<BudgetPosition> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<BudgetPosition> incomes) {
        this.incomes = incomes;
    }

    public List<BudgetPosition> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<BudgetPosition> expenses) {
        this.expenses = expenses;
    }


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
}

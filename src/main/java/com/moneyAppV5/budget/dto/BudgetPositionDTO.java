package com.moneyAppV5.budget.dto;

import com.moneyAppV5.budget.Budget;
import com.moneyAppV5.budget.BudgetPosition;
import com.moneyAppV5.category.dto.CategoryDTO;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class BudgetPositionDTO
{
    private BudgetDTO budgetDto;
    private double plannedAmount;
    private double actualAmount;
    private double balance;
    private String description;
    private Integer hash;
    private List<TransactionDTO> transactionsDto;
    private CategoryDTO category;
    private HashMap<String, Double> dailyView;
    private int usage;
    private String categoryString;

     public BudgetPositionDTO()
    {
    }

    BudgetPositionDTO(CategoryDTO category, Budget budget, Set<Transaction> transactions, double plannedAmount, double actualAmount, double balance, String description)
    {
        this.category = category;
        this.budgetDto = new BudgetDTO(budget);
//        this.transactions = transactions;
        this.plannedAmount = plannedAmount;
        this.actualAmount = actualAmount;
        this.balance = balance;
        this.description = description;
    }

    public BudgetPositionDTO(BudgetPosition position)
    {
//        this.category = position.getCategory();
//        this.budget = position.getBudget();
//        this.transactions = position.getTransactions();
        this.category = position.getCategory().toDto();
        this.categoryString = position.getCategory().getCategory();
        this.plannedAmount = position.getPlannedAmount();
        this.description = position.getDescription();
        this.hash = position.getHash();
    }

    public BudgetPositionDTO(CategoryDTO category, double plannedAmount, int hash)
    {
        this.category = category;
        this.plannedAmount = plannedAmount;
        this.hash = hash;
    }

    public BudgetPositionDTO(String category, double plannedAmount, int hash)
    {
        this.categoryString = category;
        this.plannedAmount = plannedAmount;
        this.hash = hash;
    }

    private BudgetPositionDTO(BudgetPositionDtoBuilder builder)
    {
        this.budgetDto = builder.budgetDto;
        this.plannedAmount = builder.plannedAmount;
        this.actualAmount = builder.actualAmount;
        this.balance = builder.balance;
        this.description = builder.description;
        this.hash = builder.hash;
        this.transactionsDto = builder.transactionsDto;
        this.category = builder.category;
        this.dailyView = builder.dailyView;
        this.usage = builder.usage;
        this.categoryString = builder.categoryString;
    }

    BudgetPosition toBudgetPosition()
    {
        var result = new BudgetPosition();
        result.setCategory(this.category.toCategory());
        result.setBudget(this.budgetDto.toBudget());
//        result.setTransactions(new HashSet<>(this.transactions));
        result.setPlannedAmount(this.plannedAmount);
        result.setDescription(this.description);
        result.setHash(result.hashCode());

        return result;
    }

    public BudgetDTO getBudget() {
        return budgetDto;
    }

    public void setBudget(BudgetDTO budget) {
        this.budgetDto = budget;
    }

    public double getPlannedAmount() {
        return plannedAmount;
    }

    public void setPlannedAmount(double plannedAmount) {
        this.plannedAmount = plannedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public List<TransactionDTO> getTransactionsDto() {
        return transactionsDto;
    }

    public void setTransactionsDto(List<TransactionDTO> transactionsDto) {
        this.transactionsDto = transactionsDto;
    }

    public BudgetDTO getBudgetDto() {
        return budgetDto;
    }

    public void setBudgetDto(BudgetDTO budgetDto) {
        this.budgetDto = budgetDto;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public HashMap<String, Double> getDailyView() {
        return dailyView;
    }

    public void setDailyView(HashMap<String, Double> dailyView) {
        this.dailyView = dailyView;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public String getCategoryString() {
        return categoryString;
    }

    public void setCategoryString(String categoryString) {
        this.categoryString = categoryString;
    }

    public static class BudgetPositionDtoBuilder
    {
        private BudgetDTO budgetDto;
        private double plannedAmount;
        private double actualAmount;
        private double balance;
        private String description;
        private Integer hash;
        private List<TransactionDTO> transactionsDto;
        private CategoryDTO category;
        private HashMap<String, Double> dailyView;
        private int usage;
        private String categoryString;

        public BudgetPositionDtoBuilder buildBudgetDto(BudgetDTO budgetDto)
        {
            this.budgetDto = budgetDto;

            return this;
        }

        public BudgetPositionDtoBuilder buildPlannedAmount(double plannedAmount)
        {
            this.plannedAmount = plannedAmount;

            return this;
        }

        public BudgetPositionDtoBuilder buildActualAmount(double actualAmount)
        {
            this.actualAmount = actualAmount;

            return this;
        }

        public BudgetPositionDtoBuilder buildBalance()
        {
            this.balance = this.plannedAmount - this.actualAmount;

            return this;
        }

        public BudgetPositionDtoBuilder buildDescription(String description)
        {
            this.description = description;

            return this;
        }

        public BudgetPositionDtoBuilder buildHash(int hash)
        {
            this.hash = hash;

            return this;
        }

        public BudgetPositionDtoBuilder buildTransactionsDto(List<TransactionDTO> transactionsDto)
        {
            this.transactionsDto = transactionsDto;

            return this;
        }

        public BudgetPositionDtoBuilder buildCategory(CategoryDTO category)
        {
            this.category = category;

            return this;
        }

        public BudgetPositionDtoBuilder buildDailyView(HashMap<String, Double> dailyView)
        {
            this.dailyView = dailyView;

            return this;
        }

        public BudgetPositionDtoBuilder buildUsage()
        {
            this.usage = (int)(this.actualAmount / this.plannedAmount) * 100;

            return this;
        }

        public BudgetPositionDtoBuilder buildCategoryString(String categoryString)
        {
            this.categoryString = categoryString;

            return this;
        }

        public BudgetPositionDTO build()
        {
            return new BudgetPositionDTO(this);
        }
    }
}

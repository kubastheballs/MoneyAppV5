package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MainCategoryDTO
{
    private String mainCategory;
//    private List<SubCategory> subCategories;
    private List<SubCategoryDTO> subCategories;
    private List<TransactionDTO> transactions;
    private Integer hash;

    private double actualMonthSum;
    private double actualMonthMinusOneSum;
    private double actualMonthMinusTwoSum;
    private double actualYearSum;
    private double overallSum;

    private int actualMonthCount;
    private int actualMonthMinusOneCount;
    private int actualMonthMinusTwoCount;
    private int actualYearCount;
    private int overallCount;

    MainCategoryDTO()
    {
    }

    public MainCategoryDTO(String mainCategory)
    {
        this.mainCategory = mainCategory;
    }

//    public MainCategoryDTO(String mainCategory, List<SubCategory> subCategories)
//    {
//        this.mainCategory = mainCategory;
//        this.subCategories = subCategories;
//    }

    public MainCategoryDTO(MainCategory mainCategory)
    {
        this.mainCategory = mainCategory.getMainCategory();
        this.hash = mainCategory.getHash();
    }

    public MainCategoryDTO(MainCategory mainCategory, List<SubCategory> subCategories)
    {
        this.mainCategory = mainCategory.getMainCategory();
        this.subCategories = subCategories.stream().map(SubCategoryDTO::new).collect(Collectors.toList());
    }

    public MainCategory toMainCategory()
    {
        var result = new MainCategory();
        result.setMainCategory(this.mainCategory);
        result.setHash(result.hashCode());

        return result;
    }

    @Override
    public String toString()
    {
        return this.mainCategory;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public List<SubCategoryDTO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryDTO> subCategories) {
        this.subCategories = subCategories;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public double getActualMonthSum() {
        return actualMonthSum;
    }

    public void setActualMonthSum(double actualMonthSum) {
        this.actualMonthSum = actualMonthSum;
    }

    public double getActualMonthMinusOneSum() {
        return actualMonthMinusOneSum;
    }

    public void setActualMonthMinusOneSum(double actualMonthMinusOneSum) {
        this.actualMonthMinusOneSum = actualMonthMinusOneSum;
    }

    public double getActualMonthMinusTwoSum() {
        return actualMonthMinusTwoSum;
    }

    public void setActualMonthMinusTwoSum(double actualMonthMinusTwoSum) {
        this.actualMonthMinusTwoSum = actualMonthMinusTwoSum;
    }

    public double getActualYearSum() {
        return actualYearSum;
    }

    public void setActualYearSum(double actualYearSum) {
        this.actualYearSum = actualYearSum;
    }

    public double getOverallSum() {
        return overallSum;
    }

    public void setOverallSum(double overallSum) {
        this.overallSum = overallSum;
    }

    public int getActualMonthCount() {
        return actualMonthCount;
    }

    public void setActualMonthCount(int actualMonthCount) {
        this.actualMonthCount = actualMonthCount;
    }

    public int getActualMonthMinusOneCount() {
        return actualMonthMinusOneCount;
    }

    public void setActualMonthMinusOneCount(int actualMonthMinusOneCount) {
        this.actualMonthMinusOneCount = actualMonthMinusOneCount;
    }

    public int getActualMonthMinusTwoCount() {
        return actualMonthMinusTwoCount;
    }

    public void setActualMonthMinusTwoCount(int actualMonthMinusTwoCount) {
        this.actualMonthMinusTwoCount = actualMonthMinusTwoCount;
    }

    public int getActualYearCount() {
        return actualYearCount;
    }

    public void setActualYearCount(int actualYearCount) {
        this.actualYearCount = actualYearCount;
    }

    public int getOverallCount() {
        return overallCount;
    }

    public void setOverallCount(int overallCount) {
        this.overallCount = overallCount;
    }
}

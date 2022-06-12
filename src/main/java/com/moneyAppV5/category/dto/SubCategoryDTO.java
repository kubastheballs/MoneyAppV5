package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.List;

public class SubCategoryDTO
{
    private String subCategory;
//    private String description;
//    private MainCategory mainCategory;
    private MainCategoryDTO mainCategory;
    private Integer hash;
    private List<TransactionDTO> transactions;

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

    SubCategoryDTO()
    {
    }

    public SubCategoryDTO(SubCategory subCategory)
    {
        this.subCategory = subCategory.getSubCategory();
        this.mainCategory = new MainCategoryDTO(subCategory.getMainCategory());
//        this.mainCategory = subCategory.getMainCategory();
        this.hash = subCategory.getHash();
    }

    public SubCategoryDTO(String subCategory, MainCategory mainCategory) {
        this.subCategory = subCategory;
        this.mainCategory = new MainCategoryDTO(mainCategory);
    }

//    SubCategoryDTO(String subCategory, String description)
//    {
//        this.subCategory = subCategory;
////        this.description = description;
//    }



    public SubCategory toSubCategory()
    {
        var result = new SubCategory();
        result.setSubCategory(this.subCategory);
//        result.setDescription(this.description);
        result.setMainCategory(this.mainCategory.toMainCategory());
        result.setHash(result.hashCode());

        return result;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public MainCategoryDTO getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategoryDTO mainCategory) {
        this.mainCategory = mainCategory;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    @Override
    public String toString()
    {
        return this.subCategory;
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

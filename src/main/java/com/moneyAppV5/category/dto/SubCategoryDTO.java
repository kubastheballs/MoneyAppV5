package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;

public class SubCategoryDTO
{
    private String subCategory;
//    private String description;
    private MainCategory mainCategory;
    private Integer hash;

    SubCategoryDTO()
    {
    }

    public SubCategoryDTO(SubCategory subCategory)
    {
        this.subCategory = subCategory.getSubCategory();
        this.mainCategory = subCategory.getMainCategory();
        this.hash = subCategory.getHash();
    }

    public SubCategoryDTO(String subCategory, MainCategory mainCategory) {
        this.subCategory = subCategory;
        this.mainCategory = mainCategory;
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
        result.setMainCategory(this.mainCategory);
        result.setHash(result.hashCode());

        return result;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
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
}

package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;

import java.util.List;

public class MainCategoryDTO
{
    private String mainCategory;
    private List<SubCategory> subCategories;
    private Integer hash;

    MainCategoryDTO()
    {
    }



    public MainCategoryDTO(String mainCategory)
    {
        this.mainCategory = mainCategory;
    }

    public MainCategoryDTO(String mainCategory, List<SubCategory> subCategories)
    {
        this.mainCategory = mainCategory;
        this.subCategories = subCategories;
    }

    public MainCategoryDTO(MainCategory mainCategory)
    {
        this.mainCategory = mainCategory.getMainCategory();
        this.hash = mainCategory.getHash();
    }

    public MainCategoryDTO(MainCategory mainCategory, List<SubCategory> subCategories)
    {
        this.mainCategory = mainCategory.getMainCategory();
        this.subCategories = subCategories;
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

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }
}

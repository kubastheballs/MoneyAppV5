package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.transaction.Payee;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;

public class MainCategoryDTO
{
    private String mainCategory;
//    private String description;
//    private Category category;
    private List<SubCategory> subCategories;

    MainCategoryDTO()
    {
    }



    public MainCategoryDTO(String mainCategory)
    {
        this.mainCategory = mainCategory;
    }
//TODO dlaczego taki konstruktor wyrzuca "main_category cannot be null" przy tworzeniu nowego?
    //    public MainCategoryDTO(String mainCategory)
//    {
//        new MainCategoryDTO(mainCategory, null);
//    }

    public MainCategoryDTO(String mainCategory, List<SubCategory> subCategories)
    {
        this.mainCategory = mainCategory;
//        this.description = description;
        this.subCategories = subCategories;
    }

    public MainCategoryDTO(MainCategory mainCategory)
    {
        this.mainCategory = mainCategory.getMainCategory();
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
//        result.setDescription(this.description);

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
}

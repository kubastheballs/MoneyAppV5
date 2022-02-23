package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.transaction.Payee;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

class MainCategoryDTO
{
    private String mainCategory;
    private String description;
//    private Category category;

    MainCategoryDTO()
    {
    }

    MainCategoryDTO(String mainCategory, String description)
    {
        this.mainCategory = mainCategory;
        this.description = description;
    }

    MainCategory toMainCategory()
    {
        var result = new MainCategory();
        result.setMainCategory(this.mainCategory);
        result.setDescription(this.description);

        return result;
    }
}

package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;

class SubCategoryDTO
{
    private String subCategory;
    private String description;
//    private Category category;

    SubCategoryDTO()
    {
    }

    SubCategoryDTO(String subCategory, String description)
    {
        this.subCategory = subCategory;
        this.description = description;
    }

    SubCategory toSubCategory()
    {
        var result = new SubCategory();
        result.setSubCategory(this.subCategory);
        result.setDescription(this.description);

        return result;
    }

}

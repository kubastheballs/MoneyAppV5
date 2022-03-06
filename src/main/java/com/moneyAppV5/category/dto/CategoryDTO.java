package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.category.Type;

public class CategoryDTO
{
    private MainCategory mainCategory;
    private SubCategory subCategory;
    private String main;
    private String sub;
//    private List<TransactionDTO> transactionsDTO;
    private Type type;
    private String description;

     public CategoryDTO()
    {
    }

////    CategoryDTO(MainCategoryDTO mainCategoryDTO, SubCategoryDTO subCategoryDTO, List<TransactionDTO> transactionsDTO, Type type, String description)
////    CategoryDTO(String category , List<TransactionDTO> transactionsDTO, String type, String description)
//    CategoryDTO(String category, String subCategory, String type, String description)
//    {
////        this.mainCategoryDTO = mainCategoryDTO;
////        this.subCategoryDTO = subCategoryDTO;
//        this.category = category;
////        this.transactionsDTO = transactionsDTO;
//        this.type = type;
//        this.description = description;
//    }

    public CategoryDTO(MainCategory mainCategory, SubCategory subCategory, Type type, String description)
    {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.type = type;
        this.description = description;
    }

    public CategoryDTO(String mainCategory, String subCategory, Type type, String description)
    {
        this.main = mainCategory;
        this.sub = subCategory;
        this.type = type;
        this.description = description;
    }

    public Category toCategory()
    {
        var result = new Category();

        result.setMainCategory(this.mainCategory);
        result.setSubCategory(this.subCategory);
        result.setType(this.type);
        result.setDescription(this.description);

        return result;
    }

    public CategoryDTO(Category category)
    {
        this.mainCategory = category.getMainCategory();
        this.subCategory = category.getSubCategory();
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

//    public SubCategoryDTO getSubCategoryDTO() {
//        return subCategoryDTO;
//    }
//
//    public void setSubCategoryDTO(SubCategoryDTO subCategoryDTO) {
//        this.subCategoryDTO = subCategoryDTO;
//    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

//    public List<TransactionDTO> getTransactionsDTO() {
//        return transactionsDTO;
//    }
//
//    public void setTransactionsDTO(List<TransactionDTO> transactionsDTO) {
//        this.transactionsDTO = transactionsDTO;
//    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toDisplay()
    {
        if ((this.sub).equals("-"))
            return String.format("%s", this.main);
        else
            return String.format("%s : %s", this.main, this.sub);
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}

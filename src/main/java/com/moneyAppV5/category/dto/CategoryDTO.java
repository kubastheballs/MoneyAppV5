package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.List;

public class CategoryDTO
{
    private MainCategory mainCategory;
    private MainCategoryDTO mainCategoryDto;
    private SubCategory subCategory;
    private SubCategoryDTO subCategoryDto;
    private String main;
    private String sub;
    private String category;
    private List<TransactionDTO> transactionsDTO;
    private Type type;
    private String description;
    private Integer hash;

    private ActualDataWrapperDTO wrapper;

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
        this.category = toDisplay(mainCategory.getMainCategory(), subCategory.getSubCategory());
        this.type = type;
        this.description = description;
    }

    public CategoryDTO(String mainCategory, String subCategory, Type type, String description)
    {
        this.main = mainCategory;
        this.sub = subCategory;
        this.category = toDisplay(mainCategory, subCategory);
        this.type = type;
        this.description = description;
    }

    public CategoryDTO(Category category)
    {
        this.mainCategoryDto = new MainCategoryDTO(category.getMainCategory());
        this.subCategoryDto = new SubCategoryDTO(category.getSubCategory());
        this.type = category.getType();
        this.category = toDisplay(category.getMainCategory().getMainCategory(), category.getSubCategory().getSubCategory());
        this.hash = category.getHash();
    }

    public Category toCategory()
    {
        var result = new Category();

        result.setCategory(this.category);
        result.setMainCategory(this.mainCategory);
        result.setSubCategory(this.subCategory);
        result.setType(this.type);
        result.setDescription(this.description);
        result.setHash(result.hashCode());

        return result;
    }

//    TODO czy to lepiej wyciągać z bazy tworząc stronę?

//    List<TransactionDTO> transactionsToDtos(Set<Transaction> transactions)
//    {
//        List<TransactionDTO> dtos = new ArrayList<>();
//
//        for (Transaction t : transactions)
//            dtos.add(new TransactionDTO(t));
//
//        return dtos;
//    }

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

    public String toDisplay(String main, String sub)
    {
        return String.format("%s : %s", main, sub);
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public List<TransactionDTO> getTransactionsDTO() {
        return transactionsDTO;
    }

    public void setTransactionsDTO(List<TransactionDTO> transactionsDTO) {
        this.transactionsDTO = transactionsDTO;
    }

    public MainCategoryDTO getMainCategoryDto() {
        return mainCategoryDto;
    }

    public void setMainCategoryDto(MainCategoryDTO mainCategoryDto) {
        this.mainCategoryDto = mainCategoryDto;
    }

    public SubCategoryDTO getSubCategoryDto() {
        return subCategoryDto;
    }

    public void setSubCategoryDto(SubCategoryDTO subCategoryDto) {
        this.subCategoryDto = subCategoryDto;
    }

    public ActualDataWrapperDTO getWrapper() {
        return wrapper;
    }

    public void setWrapper(ActualDataWrapperDTO wrapper) {
        this.wrapper = wrapper;
    }
}

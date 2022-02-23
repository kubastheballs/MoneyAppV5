package com.moneyAppV5.category.dto;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO
{
    private MainCategoryDTO mainCategoryDTO;
    private SubCategoryDTO subCategoryDTO;
    private List<TransactionDTO> transactionsDTO;
    private Type type;
    private String description;
//    private Set<BudgetPosition> budgetPositions;

     public CategoryDTO()
    {
    }

    CategoryDTO(MainCategoryDTO mainCategoryDTO, SubCategoryDTO subCategoryDTO, List<TransactionDTO> transactionsDTO, Type type, String description)
    {
        this.mainCategoryDTO = mainCategoryDTO;
        this.subCategoryDTO = subCategoryDTO;
        this.transactionsDTO = transactionsDTO;
        this.type = type;
        this.description = description;
    }

    public Category toCategory()
    {
        var result = new Category();
        result.setMainCategory(this.mainCategoryDTO.toMainCategory());
        result.setSubCategory(this.subCategoryDTO.toSubCategory());
        result.setTransactions(this.transactionsDTO.stream()
                .map(TransactionDTO::toTransaction)
                .collect(Collectors.toSet()));
        result.setType(this.type);
        result.setDescription(this.description);

        return result;
    }
}

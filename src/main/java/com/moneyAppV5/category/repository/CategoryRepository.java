package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.category.Type;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository
{
    List<Category> findAll();

    Optional<Category> findById(Integer id);
    Category findCategoryByHash(Integer hash);

    boolean existsById(int id);
    boolean existsByCategory(String category);

    Category save(Category entity);

    List<Category> findCategoriesByType(String type);

    List<Category> findAllExpenses();

    List<Category> findAllIncomes();


    boolean existsByMainCategoryAndSubCategoryAndType(MainCategory mainCategory, SubCategory subCategory, Type type);

    boolean existsByMainCategoryAndSubCategory(MainCategory mainCategory, SubCategory subCategory);
}

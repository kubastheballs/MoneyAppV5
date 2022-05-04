package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository
{
    List<SubCategory> findAll();
    List<SubCategory> findSubCategoriesByMainId(Integer id);

    Optional<SubCategory> findById(Integer id);
    Optional<SubCategory> findBySubCategory(String subCategory);

    boolean existsById(int id);
    boolean existsBySubCategory(String subCategory);

    SubCategory save(SubCategory entity);


//    List<SubCategory> findExpensesByMainId();
}

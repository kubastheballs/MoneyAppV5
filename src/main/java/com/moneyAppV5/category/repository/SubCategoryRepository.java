package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository
{
    List<SubCategory> findAll();

    Optional<SubCategory> findById(Integer id);

    boolean existsById(int id);

    SubCategory save(SubCategory entity);

    List<SubCategory> findSubCategoriesByMainId(Integer id);
//    List<SubCategory> findExpensesByMainId();
}

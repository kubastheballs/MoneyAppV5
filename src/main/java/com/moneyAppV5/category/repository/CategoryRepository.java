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

    boolean existsById(int id);

    Category save(Category entity);

}

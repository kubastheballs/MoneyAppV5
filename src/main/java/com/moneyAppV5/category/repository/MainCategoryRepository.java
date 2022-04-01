package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.Type;

import java.util.List;
import java.util.Optional;

public interface MainCategoryRepository
{
    List<MainCategory> findAll();

    Optional<MainCategory> findById(Integer id);

    boolean existsById(int id);

    MainCategory save(MainCategory entity);

//    List<MainCategory> findAllWithType();
//    List<MainCategory> findAllIncomes();
//    List<MainCategory> findAllWithType(String type);
//    List<MainCategory> findAllExpenses();

    List<MainCategory> findByType(String type);
}

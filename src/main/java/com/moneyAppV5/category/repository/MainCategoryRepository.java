package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.MainCategory;

import java.util.List;
import java.util.Optional;

public interface MainCategoryRepository
{
    List<MainCategory> findAll();

    Optional<MainCategory> findById(Integer id);

    boolean existsById(int id);

    MainCategory save(MainCategory entity);
}

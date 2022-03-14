package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlCategoryRepository extends CategoryRepository, JpaRepository<Category, Integer>
{
//    TODO jak zrobić aby przekazywać enum type jako parametr

    @Override
    @Query(nativeQuery = true, value = "select * from CATEGORIES where type = 'EXPENSE'")
    List<Category> findAllExpenses();

    @Override
    @Query(nativeQuery = true, value = "select * from CATEGORIES where type = 'INCOME'")
    List<Category> findAllIncomes();

//    @Override
//    @Query(nativeQuery = true, value = "select * from MAIN_CATEGORIES")
//    List<MainCategory> findAllMainCategories();
//    List<SubCategory> findAllSubCategories();
//
//
//    Optional<MainCategory> findMainCategoryById(Integer id);
//    Optional<SubCategory> findSubCategoryById(Integer id);
//
//
//    @Override
//    @Query(nativeQuery = true, value = "insert into MAIN_CATEGORIES values ( :entity.id, :entity.mainCategory, :entity.description)")
//    MainCategory save(MainCategory entity);
//    SubCategory save(SubCategory entity);
}

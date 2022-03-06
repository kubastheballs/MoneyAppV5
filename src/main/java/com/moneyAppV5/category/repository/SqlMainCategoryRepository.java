package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlMainCategoryRepository extends MainCategoryRepository, JpaRepository<MainCategory, Integer>
{
//    TODO określenie typu przez parametr z metody wywala błąd hex string cośtam - jak to ogarnąć?
    @Override
    @Query(nativeQuery = true, value = "select distinct main_categories.id, MAIN_CATEGORIES.MAIN_CATEGORY from main_categories " +
            "inner join categories on main_categories.id=categories.main_category_id and categories.type='INCOME'")
//    @Query(nativeQuery = true, value = "select * from main_categories inner join categories on main_categories.id=categories.main_category_id and categories.type=:type")
//    List<MainCategory> findAllWithType();
    List<MainCategory> findAllIncomes();
//    List<MainCategory> findAllWithType(@Param("t")Type type);

    @Override
    @Query(nativeQuery = true, value = "select distinct main_categories.id, MAIN_CATEGORIES.MAIN_CATEGORY from main_categories " +
            "inner join categories on main_categories.id=categories.main_category_id and categories.type='EXPENSE'")
    List<MainCategory> findAllExpenses();
}

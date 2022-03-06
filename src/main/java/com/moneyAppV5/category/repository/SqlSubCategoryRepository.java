package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlSubCategoryRepository extends SubCategoryRepository, JpaRepository<SubCategory, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select distinct * from sub_categories where MAIN_CATEGORY_ID=:id")
    List<SubCategory> findSubCategoriesByMainId(@Param("id") Integer id);
}

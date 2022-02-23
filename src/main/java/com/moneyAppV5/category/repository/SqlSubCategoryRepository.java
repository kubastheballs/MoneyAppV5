package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlSubCategoryRepository extends SubCategoryRepository, JpaRepository<SubCategory, Integer>
{

}

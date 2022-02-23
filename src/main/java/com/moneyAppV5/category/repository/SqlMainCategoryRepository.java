package com.moneyAppV5.category.repository;

import com.moneyAppV5.category.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlMainCategoryRepository extends MainCategoryRepository, JpaRepository<MainCategory, Integer>
{

}

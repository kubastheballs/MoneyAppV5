package com.moneyAppV5.category.service;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.category.repository.CategoryRepository;
import com.moneyAppV5.category.repository.MainCategoryRepository;
import com.moneyAppV5.category.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    private CategoryRepository repository;
    private MainCategoryRepository mainCategoryRepository;
    private SubCategoryRepository subCategoryRepository;

    CategoryService(CategoryRepository repository, MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository)
    {
        this.repository = repository;
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public MainCategory createMainCategory(final MainCategory toSave)
    {
        return this.mainCategoryRepository.save(toSave);
    }
    public SubCategory createSubCategory(final SubCategory toSave)
    {
        return this.subCategoryRepository.save(toSave);
    }

    public Category createCategory(final Category toSave)
    {
        return this.repository.save(toSave);
    }

    public Optional<Category> readCategoryById(int id)
    {
        return this.repository.findById(id);
    }

    public boolean existsById(int id)
    {
        return this.repository.existsById(id);
    }

    public List<MainCategory> readAllMainCategories()
    {
        return this.mainCategoryRepository.findAll();
    }
    public List<SubCategory> readAllSubCategories()
    {
        return this.subCategoryRepository.findAll();
    }

    public List<Category> readAllCategories()
    {
        return this.repository.findAll();
    }
}

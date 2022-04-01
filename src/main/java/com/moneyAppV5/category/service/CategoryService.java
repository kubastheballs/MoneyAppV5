package com.moneyAppV5.category.service;

import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.category.dto.CategoryDTO;
import com.moneyAppV5.category.dto.MainCategoryDTO;
import com.moneyAppV5.category.dto.SubCategoryDTO;
import com.moneyAppV5.category.repository.CategoryRepository;
import com.moneyAppV5.category.repository.MainCategoryRepository;
import com.moneyAppV5.category.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public MainCategory createMainCategory(final MainCategoryDTO toSave)
    {
        return this.mainCategoryRepository.save(toSave.toMainCategory());
    }
//    public SubCategory createSubCategory(final SubCategory toSave)
//    {
//        return this.subCategoryRepository.save(toSave);
//    }
    public SubCategory createSubCategory(final SubCategoryDTO toSave)
    {
        return this.subCategoryRepository.save(toSave.toSubCategory());
    }

//    public Category createCategory(final Category toSave)
//    {
//        return this.repository.save(toSave);
//    }
    public Category createCategory(final CategoryDTO toSave)
    {
        MainCategory main = null;
        boolean isMain = false;

        for (MainCategory m : this.mainCategoryRepository.findAll())
        {
            if ((toSave.getMain()).equals(m.getMainCategory()))
            {
                main = m;
                isMain = true;
                break;
            }
        }

        if (!isMain)
            main = createMainCategory(new MainCategoryDTO(toSave.getMain()));

        SubCategory sub = null;
        boolean isSub = false;

        for (SubCategory s : this.subCategoryRepository.findAll())
        {
            if ((toSave.getSub()).equals(s.getSubCategory()))
            {
                sub = s;
                isSub = true;
                break;
            }
        }

        if (!isSub)
            sub = createSubCategory(new SubCategoryDTO(toSave.getSub(), main));

        var result = new CategoryDTO(main , sub, toSave.getType(), toSave.getDescription());

        return this.repository.save(result.toCategory());
    }

    public Optional<Category> readCategoryById(int id)
    {
        return this.repository.findById(id);
    }

    public boolean existsById(int id)
    {
        return this.repository.existsById(id);
    }

//    public List<MainCategory> readAllMainCategories()
//    {
//        return this.mainCategoryRepository.findAll();
//    }
//    public List<SubCategory> readAllSubCategories()
//    {
//        return this.subCategoryRepository.findAll();
//    }


    public List<Category> readAllCategories()
    {
        return this.repository.findAll();
    }

    public List<CategoryDTO> readAllCategoriesDTO()
    {
        List<CategoryDTO> dtos = new ArrayList<>();

         for(Category cat : readAllCategories())
             dtos.add(new CategoryDTO(cat));

        return dtos;
    }

    public List<Category> readCategoriesByType(Type type)
    {
        return this.repository.findCategoriesByType(type.name());
    }

    public List<CategoryDTO> readCategoriesDTOByType(Type type)
    {
        List<CategoryDTO> dtos = new ArrayList<>();

        for(Category cat : readCategoriesByType(type))
            dtos.add(new CategoryDTO(cat));

        return dtos;
    }

//    public List<MainCategoryDTO> readMainCategoriesDtoOfIncomes()
//    {
////        List<MainCategoryDTO> dtos = new ArrayList<>();
////
////        for(MainCategory cat : readMainCategoriesOfIncomes())
////        {
////            var dto = new MainCategoryDTO(cat, readSubCategoriesByMainId(cat.getId()));
////
////            if (!dtos.contains(dto))
////                dtos.add(dto);
////        }
////
////        return dtos;
//
//        return createMainCategoriesDtoList(readMainCategoriesOfIncomes());
//    }

    public List<MainCategoryDTO> readMainCategoriesDtoByType(Type type)
    {
//        List<MainCategoryDTO> dtos = new ArrayList<>();
//
//        for(MainCategory cat : readMainCategoriesOfExpenses())
//        {
//            var dto = new MainCategoryDTO(cat, readSubCategoriesByMainId(cat.getId()));
//
//            if (!dtos.contains(dto))
//                dtos.add(dto);
//        }
//
//        return dtos;

        return createMainCategoriesDtoList(readMainCategoriesByType(type));
    }

    private List<MainCategoryDTO> createMainCategoriesDtoList(List<MainCategory> list)
    {
        List<MainCategoryDTO> dtos = new ArrayList<>();

        for(MainCategory cat : list)
        {
            var dto = new MainCategoryDTO(cat, readSubCategoriesByMainId(cat.getId()));

            if (!dtos.contains(dto))
                dtos.add(dto);
        }

        return dtos;
    }
//
//    public List<MainCategory> readMainCategoriesOfIncomes()
//    {
//         return this.mainCategoryRepository.findAllIncomes();
//    }
//
//    public List<MainCategory> readMainCategoriesOfExpenses()
//    {
//        return this.mainCategoryRepository.findAllExpenses();
//    }

    public List<MainCategory> readAllMainCategories()
    {
        return this.mainCategoryRepository.findAll();
    }

    public List<MainCategoryDTO> readAllMainCategoriesDTO()
    {
        List<MainCategoryDTO> dtos = new ArrayList<>();

        for(MainCategory cat : readAllMainCategories())
            dtos.add(new MainCategoryDTO(cat));

        return dtos;
    }

    public List<SubCategory> readAllSubCategories()
    {
        return this.subCategoryRepository.findAll();
    }

    public List<SubCategoryDTO> readAllSubCategoriesDTO()
    {
        List<SubCategoryDTO> dtos = new ArrayList<>();

        for(SubCategory cat : readAllSubCategories())
            dtos.add(new SubCategoryDTO(cat));

        return dtos;
    }

    public List<SubCategory> readSubCategoriesByMainId(Integer id)
    {
        return this.subCategoryRepository.findSubCategoriesByMainId(id);
    }

    public List<SubCategoryDTO> readSubCategoriesDtoByMainId(Integer id)
    {
        List<SubCategoryDTO> dtos = new ArrayList<>();

        for(SubCategory cat : readSubCategoriesByMainId(id))
            dtos.add(new SubCategoryDTO(cat));

        return dtos;
    }

    public List<Category> readExpenseCategories()
    {
        return this.repository.findAllExpenses();
    }

    public List<Category> readIncomeCategories()
    {
        return this.repository.findAllIncomes();
    }

    List<MainCategory> readMainCategoriesByType(Type type)
    {
        return this.mainCategoryRepository.findByType(type.name());
    }



//    public List<String> readSubCategoriesByMainCategory(String main)
//    {
//        List<String> subs = new ArrayList<>();
//
//        for(CategoryDTO dto : readAllCategoriesDTO())
//            if ((dto.getMainCategory()).equals(main))
//                subs.add(dto.getSubCategory());
//
//        return subs;
//    }


}

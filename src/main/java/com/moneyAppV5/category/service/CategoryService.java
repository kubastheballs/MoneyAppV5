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
import com.moneyAppV5.transaction.service.TransactionService;
import com.moneyAppV5.utils.UtilService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService
{
    private final CategoryRepository repository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final TransactionService transactionService;
    private final UtilService utilService;

    CategoryService(CategoryRepository repository, MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository,
                    TransactionService transactionService, UtilService utilService)
    {
        this.repository = repository;
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.transactionService = transactionService;
        this.utilService = utilService;
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

    MainCategory readMainCategoryByName(String name)
    {
        return this.mainCategoryRepository.findByMainCategory(name).orElse(null);
    }

    SubCategory readSubCategoryByName(String name)
    {
        return this.subCategoryRepository.findBySubCategory(name).orElse(null);
    }

//    public Category createCategory(final Category toSave)
//    {
//        return this.repository.save(toSave);
//    }
    public Category createCategory(final CategoryDTO toSave)
    {
        MainCategory main = this.readMainCategoryByName(toSave.getMain());
        if (main == null)
            main = createMainCategory(new MainCategoryDTO(toSave.getMain()));

        SubCategory sub = readSubCategoryByName(toSave.getSub());
        if (sub == null)
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

    public List<CategoryDTO> readAllCategoriesDto()
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

    public List<CategoryDTO> readCategoriesDtoByType(Type type)
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

    public Category readCategoryByHash(Integer hash)
    {
        return this.repository.findCategoryByHash(hash);
    }

    public CategoryDTO readCategoryDtoByHash(Integer hash)
    {
        return new CategoryDTO(readCategoryByHash(hash));
    }

    public boolean existsByCategory(String category)
    {
        return this.repository.existsByCategory(category);
    }

    public boolean existsInDatabase(CategoryDTO dto)
    {
        return this.repository.existsByMainCategoryAndSubCategoryAndType(readMainCategoryByName(dto.getMain()), readSubCategoryByName(dto.getSub()), dto.getType());
    }

    public double sumTransactionsByActualMonthAndCategory(Category category, Integer month, Integer year)
    {
        return this.transactionService.sumActualMonthTransactionsByCategory(category, month, year);
    }

    public double sumOverallTransactionsByCategory(Category category)
    {
        return this.transactionService.sumOverallTransactionsByCategory(category);
    }

    public MainCategory readMainCategoryByHash(int hash)
    {
        return this.mainCategoryRepository.findByHash(hash).orElseThrow();
    }

    public MainCategoryDTO readMainCategoryAsDto(MainCategory main)
    {
        var mainCat = new MainCategoryDTO(main);

        mainCat.setSubCategories(readSubCategoriesByMainCategoryId(main.getId()).stream().map(SubCategoryDTO::new).collect(Collectors.toList()));
//TODO dlaczego wyrzuca numberformat exception?
        //        mainCat.setSubCategoriesDto(main.getSubCategories().stream().map(SubCategoryDTO::new).collect(Collectors.toList()));
        var transactions = this.transactionService.readTransactionsByMainCategoryIdAsDto(main.getId());
        mainCat.setTransactions(transactions);

        mainCat.setActualMonthSum(this.utilService.sumByListAndMonth(transactions, this.utilService.getActualMonthValue(), this.utilService.getActualYear()));
        mainCat.setActualMonthMinusOneSum(this.utilService.sumByListAndMonth(transactions, this.utilService.getActualMonthValue() - 1, this.utilService.getActualYear()));
        mainCat.setActualMonthMinusTwoSum(this.utilService.sumByListAndMonth(transactions, this.utilService.getActualMonthValue() - 2, this.utilService.getActualYear()));
        mainCat.setActualYearSum(this.utilService.sumByListAndYear(transactions, this.utilService.getActualYear()));
        mainCat.setOverallSum(this.utilService.sumByList(transactions));

        mainCat.setActualMonthCount(this.utilService.countByListAndMonth(transactions, this.utilService.getActualMonthValue(), this.utilService.getActualYear()));
        mainCat.setActualMonthMinusOneCount(this.utilService.countByListAndMonth(transactions, this.utilService.getActualMonthValue() - 1, this.utilService.getActualYear()));
        mainCat.setActualMonthMinusTwoCount(this.utilService.countByListAndMonth(transactions, this.utilService.getActualMonthValue() - 2, this.utilService.getActualYear()));
        mainCat.setActualYearCount(this.utilService.countByListAndYear(transactions, this.utilService.getActualYear()));
        mainCat.setOverallCount(this.utilService.countByList(transactions));

        return mainCat;
    }

    public SubCategoryDTO readSubCategoryAsDto(SubCategory sub)
    {
        var subCat = new SubCategoryDTO(sub);

//TODO dlaczego wyrzuca numberformat exception?
        //        mainCat.setSubCategoriesDto(main.getSubCategories().stream().map(SubCategoryDTO::new).collect(Collectors.toList()));
        var transactions = this.transactionService.readTransactionsBySubCategoryIdAsDto(sub.getId());
        subCat.setTransactions(transactions);

        subCat.setActualMonthSum(this.utilService.sumByListAndMonth(transactions, this.utilService.getActualMonthValue(), this.utilService.getActualYear()));
        subCat.setActualMonthMinusOneSum(this.utilService.sumByListAndMonth(transactions, this.utilService.getActualMonthValue() - 1, this.utilService.getActualYear()));
        subCat.setActualMonthMinusTwoSum(this.utilService.sumByListAndMonth(transactions, this.utilService.getActualMonthValue() - 2, this.utilService.getActualYear()));
        subCat.setActualYearSum(this.utilService.sumByListAndYear(transactions, this.utilService.getActualYear()));
        subCat.setOverallSum(this.utilService.sumByList(transactions));

        subCat.setActualMonthCount(this.utilService.countByListAndMonth(transactions, this.utilService.getActualMonthValue(), this.utilService.getActualYear()));
        subCat.setActualMonthMinusOneCount(this.utilService.countByListAndMonth(transactions, this.utilService.getActualMonthValue() - 1, this.utilService.getActualYear()));
        subCat.setActualMonthMinusTwoCount(this.utilService.countByListAndMonth(transactions, this.utilService.getActualMonthValue() - 2, this.utilService.getActualYear()));
        subCat.setActualYearCount(this.utilService.countByListAndYear(transactions, this.utilService.getActualYear()));
        subCat.setOverallCount(this.utilService.countByList(transactions));

        return subCat;
    }

    private List<SubCategory> readSubCategoriesByMainCategoryId(int mainCatId)
    {
        return this.subCategoryRepository.findSubCategoriesByMainId(mainCatId);
    }

    public SubCategoryDTO readSubCategoryByHashAsDto(Integer hash)
    {
        return new SubCategoryDTO(this.subCategoryRepository.findSubCategoryByHash(hash).orElseThrow());
    }

    public SubCategory readSubCategoryBudgetByHash(int hash)
    {
       return this.subCategoryRepository.findSubCategoryByHash(hash).orElseThrow();
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

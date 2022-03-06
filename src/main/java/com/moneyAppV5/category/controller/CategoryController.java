package com.moneyAppV5.category.controller;

import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.Type;
import com.moneyAppV5.category.dto.CategoryDTO;
import com.moneyAppV5.category.dto.MainCategoryDTO;
import com.moneyAppV5.category.dto.SubCategoryDTO;
import com.moneyAppV5.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
class CategoryController
{
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    CategoryService service;

    @GetMapping()
    String showCategories(Model model)
    {
        model.addAttribute("category", new CategoryDTO());
        return "categories";
    }

    CategoryController(CategoryService service)
    {
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"}, path = "/main")
    ResponseEntity<List<MainCategory>> readAllMainCategories() {
        logger.warn("Exposing all the main categories!");
        return ResponseEntity.ok(this.service.readAllMainCategories());
    }

//    @GetMapping(params = {"!sort", "!page", "!size"}, path = "/sub")
//    ResponseEntity<List<String>> readAllSubCategories() {
//        logger.warn("Exposing all the sub categories!");
//        return ResponseEntity.ok(this.service.readAllSubCategories());
//    }

//    @GetMapping(params = {"!sort", "!page", "!size"})
//    ResponseEntity<List<Category>> readAllCategories() {
//        logger.warn("Exposing all the categories!");
//        return ResponseEntity.ok(this.service.readAllCategories());
//    }

//    @PostMapping("/main")
//    ResponseEntity<MainCategory> createMainCategory(@RequestBody @Valid MainCategory toCreate) {
//        MainCategory result = this.service.createMainCategory(toCreate);
//
//        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
//    }
//
//    @PostMapping("/sub")
//    ResponseEntity<SubCategory> createSubCategory(@RequestBody @Valid SubCategory toCreate) {
//        SubCategory result = this.service.createSubCategory(toCreate);
//
//        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
//    }


//    @PostMapping()
//    ResponseEntity<Category> createCategory(@RequestBody @Valid Category toCreate) {
//        Category result = this.service.createCategory(toCreate);
//
//        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
//    }

    @PostMapping()
    String addCategory(@ModelAttribute("category") @Valid CategoryDTO current, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
            return "categories";
//        TODO odświeżenie strony (F5) powoduje ponowne dodanie do bazy jak temu zapobiec?

        this.service.createCategory(current);
        model.addAttribute("category", new CategoryDTO());
        model.addAttribute("categories", getCategoriesDTO());
        model.addAttribute("incomeCategories", getIncomeCategoriesDTO());
        model.addAttribute("expenseCategories", getExpenseCategoriesDTO());
        model.addAttribute("message", "Dodano kategorię!");

        return "categories";
    }

    @ModelAttribute("categories")
    List<CategoryDTO> getCategoriesDTO()
    {
        return this.service.readAllCategoriesDTO();
    }

//    @ModelAttribute("subCategories")
//    List<String> getSubCategoriesByMainCategory(String main)
//    {
//        return this.service.readSubCategoriesByMainCategory(main);
//    }

//    @ModelAttribute("incomeCategories")
//    List<CategoryDTO> getIncomeCategoriesDTO()
////    List<String> getIncomeCategoriesDTO()
//    {
//        return this.service.readCategoriesDTOByType(Type.INCOME);
////        return this.service.readMainCategoriesByType(Type.INCOME);
//    }

    @ModelAttribute("mainCategories")
    List<MainCategoryDTO> getMainCategoriesDTO()
    {
        return this.service.readAllMainCategoriesDTO();
    }

    @ModelAttribute("subCategories")
    List<SubCategoryDTO> getSubCategoriesDTOByMainId(Integer id)
    {
        return this.service.readSubCategoriesDtoByMainId(id);
    }


    @ModelAttribute("incomeCategories")
    List<MainCategoryDTO> getIncomeCategoriesDTO()
//    List<String> getIncomeCategoriesDTO()
    {
          return this.service.readMainCategoriesDtoOfIncomes();
//        return this.service.readMainCategoriesByType(Type.INCOME);
    }

    @ModelAttribute("expenseCategories")
    List<CategoryDTO> getExpenseCategoriesDTO()
    {
       return this.service.readCategoriesDTOByType(Type.EXPENSE);
    }

}

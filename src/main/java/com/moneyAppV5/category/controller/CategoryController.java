package com.moneyAppV5.category.controller;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.MainCategory;
import com.moneyAppV5.category.SubCategory;
import com.moneyAppV5.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
class CategoryController
{
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    CategoryService service;

    CategoryController(CategoryService service)
    {
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"}, path = "main")
    ResponseEntity<List<MainCategory>> readAllMainCategories() {
        logger.warn("Exposing all the main categories!");
        return ResponseEntity.ok(this.service.readAllMainCategories());
    }

    @GetMapping(params = {"!sort", "!page", "!size"}, path = "/sub")
    ResponseEntity<List<SubCategory>> readAllSubCategories() {
        logger.warn("Exposing all the sub categories!");
        return ResponseEntity.ok(this.service.readAllSubCategories());
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Category>> readAllCategories() {
        logger.warn("Exposing all the sub categories!");
        return ResponseEntity.ok(this.service.readAllCategories());
    }

    @PostMapping("/main")
    ResponseEntity<MainCategory> createMainCategory(@RequestBody @Valid MainCategory toCreate) {
        MainCategory result = this.service.createMainCategory(toCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PostMapping("/sub")
    ResponseEntity<SubCategory> createSubCategory(@RequestBody @Valid SubCategory toCreate) {
        SubCategory result = this.service.createSubCategory(toCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PostMapping()
    ResponseEntity<Category> createCategory(@RequestBody @Valid Category toCreate) {
        Category result = this.service.createCategory(toCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}

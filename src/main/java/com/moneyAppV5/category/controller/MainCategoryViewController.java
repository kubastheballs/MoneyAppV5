package com.moneyAppV5.category.controller;

import com.moneyAppV5.category.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainCategoryView/{hash}")
public class MainCategoryViewController
{
    private final CategoryService service;

    public MainCategoryViewController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    String showMainCategoryView(@PathVariable Integer hash, Model model)
    {
//        TODO
        return "mainCategoryView";
    }
}

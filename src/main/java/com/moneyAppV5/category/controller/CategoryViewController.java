package com.moneyAppV5.category.controller;

import com.moneyAppV5.category.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoryView/{hash}")
public class CategoryViewController
{
    private final CategoryService service;

    public CategoryViewController(CategoryService service)
    {
        this.service = service;
    }

    @GetMapping
    String showCategoryView(@PathVariable Integer hash, Model model)
    {
        var result = this.service.readCategoryDtoByHash(hash);

        model.addAttribute("message", String.format("Kategoria: %s", result.getCategory()));
        model.addAttribute("category", result);

        return "categoryView";
    }
}